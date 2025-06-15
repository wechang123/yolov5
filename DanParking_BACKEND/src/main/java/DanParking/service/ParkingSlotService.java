package DanParking.service;

import DanParking.dto.request.ParkingSlotRequestDTO;
import DanParking.dto.request.ParkingSlotsInitDTO;
import DanParking.dto.response.ParkingSlotResponseDTO;
import DanParking.entity.ParkingSlot;
import DanParking.repository.ParkingLotJpaRepository;
import DanParking.repository.ParkingSlotJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSlotService {
    @Autowired
    private ParkingSlotJpaRepository parkingSlotJpaRepository;
    @Autowired
    private ParkingLotJpaRepository parkingLotJpaRepository;

    @Transactional
    public List<ParkingSlotResponseDTO> saveParkingSlots(ParkingSlotsInitDTO parkingSlotsInitDTO) {
        List<ParkingSlot> savedParkingSlots = new ArrayList<>();
        for(ParkingSlotRequestDTO parkingSlotRequestDTO : parkingSlotsInitDTO.getSlots()){
            if(parkingLotJpaRepository.findById(parkingSlotRequestDTO.getParkingLotId()).isEmpty()){
                throw new IllegalArgumentException("parkingLotId "+parkingSlotRequestDTO.getParkingLotId()+" 에 해당하는 주차장이 존재하지 않음.");
            }
            ParkingSlot parkingSlot = new ParkingSlot(
                    parkingSlotRequestDTO.getParkingLotId(),
                    parkingSlotRequestDTO.getSlotNumber(),
                    parkingSlotRequestDTO.getIsAvailable());
            if(parkingSlotJpaRepository.findByParkingLotIdAndSlotNumber(parkingSlot.getParkingLotId(), parkingSlot.getSlotNumber()).isPresent()){
                throw new IllegalArgumentException("parkingLotId "+parkingSlot.getParkingLotId()+", parkingSlotId "+parkingSlot.getSlotNumber()+" 에 해당하는 parkingSlot 이 이미 존재");
            }
            parkingSlotJpaRepository.save(parkingSlot);
            savedParkingSlots.add(parkingSlotJpaRepository.save(parkingSlot));
        }
        return ParkingSlotResponseDTO.fromEntityList(savedParkingSlots);
    }

    public List<ParkingSlotResponseDTO> findAllParkingSlots() {
        List<ParkingSlot> parkingSlots = parkingSlotJpaRepository.findAll();
        return ParkingSlotResponseDTO.fromEntityList(parkingSlots);
    }

    public List<ParkingSlotResponseDTO> findParkingSlotsByParkingLotId(Long parkingLotId) {
        List<ParkingSlot> parkingSlots = parkingSlotJpaRepository.findByParkingLotId(parkingLotId);
        return ParkingSlotResponseDTO.fromEntityList(parkingSlots);
    }

    @Transactional
    public ParkingSlotResponseDTO updateParkingSlot(ParkingSlotRequestDTO parkingSlotRequestDTO) {
        ParkingSlot parkingSlot = parkingSlotJpaRepository.findByParkingLotIdAndSlotNumber(
                parkingSlotRequestDTO.getParkingLotId(),
                parkingSlotRequestDTO.getSlotNumber())
                .orElseThrow(()-> new IllegalArgumentException("업데이트할 ParkingSlot 을 찾을 수 없음."));
        parkingSlot.updateParkingSlot(parkingSlotRequestDTO.getIsAvailable());
        return ParkingSlotResponseDTO.fromEntity(parkingSlot);
    }
}
