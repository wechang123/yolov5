package DanParking.service;

import DanParking.dto.request.FavoriteParkingLotCreateDTO;
import DanParking.dto.response.FavoriteParkingLotResponseDTO;
import DanParking.entity.FavoriteParkingLot;
import DanParking.entity.ParkingLot;
import DanParking.entity.User;
import DanParking.repository.FavoriteParkingLotJpaRepository;
import DanParking.repository.ParkingLotJpaRepository;
import DanParking.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteParkingLotService {
    @Autowired
    private FavoriteParkingLotJpaRepository favoriteParkingLotJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ParkingLotJpaRepository parkingLotJpaRepository;

    @Transactional
    public FavoriteParkingLotResponseDTO saveFavoriteParkingLot(Long userId, FavoriteParkingLotCreateDTO favoriteParkingLotCreateDTO) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+" 에 해당하는 user 없음."));
        if(favoriteParkingLotJpaRepository.findByParkingLotId(favoriteParkingLotCreateDTO.getParkingLotId()).isPresent()){
            throw new IllegalArgumentException("parkingLotId: "+favoriteParkingLotCreateDTO.getParkingLotId()+" 에 해당하는 주차장을 이미 즐겨찾기 중임");
        }
        ParkingLot parkingLot = parkingLotJpaRepository.findById(favoriteParkingLotCreateDTO.getParkingLotId())
                .orElseThrow(()-> new IllegalArgumentException("parkingLotId: "+favoriteParkingLotCreateDTO.getParkingLotId()+" 에 해당하는 parkingLot 없음"));

        FavoriteParkingLot favoriteParkingLot = new FavoriteParkingLot(user, parkingLot);
        user.getFavoriteParkingLotList().add(favoriteParkingLot);

        FavoriteParkingLot savedFavoriteParkingLot = favoriteParkingLotJpaRepository.save(favoriteParkingLot);
        return new FavoriteParkingLotResponseDTO(
                savedFavoriteParkingLot.getId(),
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getLocation(),
                parkingLot.getTotalSlots(),
                parkingLot.getAvailableSlots()
        );
    }

    public List<FavoriteParkingLotResponseDTO> findMyFavoriteParkingLots(Long userId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+" 에 해당하는 user 없음."));
        List<FavoriteParkingLot> favoriteParkingLotList = user.getFavoriteParkingLotList();

        List<FavoriteParkingLotResponseDTO> favoriteParkingLotResponseDTOList = new ArrayList<>();
        for(FavoriteParkingLot favoriteParkingLot : favoriteParkingLotList){
            favoriteParkingLotResponseDTOList.add(
                    new FavoriteParkingLotResponseDTO(
                            favoriteParkingLot.getId(),
                            favoriteParkingLot.getParkingLot().getId(),
                            favoriteParkingLot.getParkingLot().getName(),
                            favoriteParkingLot.getParkingLot().getLocation(),
                            favoriteParkingLot.getParkingLot().getTotalSlots(),
                            favoriteParkingLot.getParkingLot().getAvailableSlots()
                    )
            );
        }
        return favoriteParkingLotResponseDTOList;
    }

    @Transactional
    public void deleteFavoriteParkingLot(Long userId, Long favoriteParkingLotId) {
        User user = userJpaRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("userId: "+userId+" 에 해당하는 user 없음."));
        FavoriteParkingLot favoriteParkingLot = favoriteParkingLotJpaRepository.findById(favoriteParkingLotId)
                .orElseThrow(()-> new IllegalArgumentException("favoriteParkingLotId: "+favoriteParkingLotId+" 에 해당하는 favoriteParkingLot 없음."));
        if(!user.getId().equals(favoriteParkingLot.getUser().getId())){
            throw new IllegalArgumentException("userId: "+userId+" 사용자의 favoriteParkingLot 이 아닙니다.");
        }
        user.getFavoriteParkingLotList().remove(favoriteParkingLot);
        favoriteParkingLotJpaRepository.delete(favoriteParkingLot);
    }
}
