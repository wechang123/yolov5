package DanParking.service;

import DanParking.dto.request.ParkingLotRequestDTO;
import DanParking.dto.request.ParkingLotsInitDTO;
import DanParking.dto.response.ParkingLotResponseDTO;
import DanParking.entity.ParkingLot;
import DanParking.repository.ParkingLotJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotJpaRepository parkingLotJpaRepository;

    @Transactional
    public List<ParkingLotResponseDTO> saveParkingLots(ParkingLotsInitDTO parkingLotsInitDTO) {
        List<ParkingLot> savedParkingLots = new ArrayList<>();
        for(ParkingLotRequestDTO parkingLotRequestDTO : parkingLotsInitDTO.getParkingLots()){
            ParkingLot parkingLot = new ParkingLot(
                    parkingLotRequestDTO.getName(),
                    parkingLotRequestDTO.getLocation(),
                    parkingLotRequestDTO.getTotalSlots(),
                    parkingLotRequestDTO.getAvailableSlots()
            );
            savedParkingLots.add(parkingLotJpaRepository.save(parkingLot));
        }
        return ParkingLotResponseDTO.fromEntityList(savedParkingLots);
    }

    public List<ParkingLotResponseDTO> findAllParkingLots() {
        List<ParkingLot> parkingLotList = parkingLotJpaRepository.findAll();
        return ParkingLotResponseDTO.fromEntityList(parkingLotList);
    }
}
