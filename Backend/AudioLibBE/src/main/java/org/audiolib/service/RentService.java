package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.RentReceiveDTO;
import org.audiolib.entity.AudioCarrier;
import org.audiolib.entity.History;
import org.audiolib.entity.HistoryPK;
import org.audiolib.entity.Transact;
import org.audiolib.repository.CarrierRepository;
import org.audiolib.repository.HistoryRepository;
import org.audiolib.repository.TransactRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RentService {
    private final HistoryRepository historyRepository;
    private final TransactRepository transactRepository;
    private final CarrierRepository carrierRepository;

    private boolean carrierAvailable(Long carrierId) {
        AudioCarrier carrier = carrierRepository.findById(carrierId).get();
        return carrier.getAmtAvailable() == null || carrier.getAmtAvailable() > 0;
    }

    public void createRent(RentReceiveDTO rentReceiveDTO) {
        Transact tRecord = new Transact(
                null,
                rentReceiveDTO.userId(),
                rentReceiveDTO.audioCarrierId(),
                Date.valueOf(
                        LocalDate.now()
                ),
                rentReceiveDTO.dateEndOfRent()
        );
        if (carrierAvailable(tRecord.getAudioCarrierId())) {
//            AudioCarrier carrier = carrierRepository.findById(tRecord.getAudioCarrierId()).get();
//            if(carrier.getCarrier() == AudioCarrier.Carriers.digital)
//            {
//                carrier.setAmtAvailable(carrier.getAmtAvailable() - 1);
//                carrierRepository.save(carrier);
//            }
            transactRepository.save(tRecord);
//            String userId = rentReceiveDTO.userId();
//            Long audioId = carrierRepository.findAudioIdByCarrier(rentReceiveDTO.audioCarrierId());
//            History hRecord = new History();
//            hRecord.setUserId(userId);
//            hRecord.setAudioId(audioId);
//            if (historyRepository.existsById(new HistoryPK(userId, audioId))) {
//                hRecord = historyRepository.findById(new HistoryPK(userId, audioId)).get();
//                hRecord.setAmtListened(hRecord.getAmtListened() + 1);
//            } else {
//                hRecord.setAmtListened(1);
//            }
//            historyRepository.save(hRecord);
        }
    }
}
