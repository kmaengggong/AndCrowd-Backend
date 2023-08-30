package com.fiveis.andcrowd.service.etc;

import com.fiveis.andcrowd.dto.etc.InfoBoardDTO;
import com.fiveis.andcrowd.entity.etc.InfoBoard;
import com.fiveis.andcrowd.repository.etc.InfoBoardJPARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fiveis.andcrowd.dto.etc.InfoBoardDTO.convertToInfoFindDTO;

@Service
public class InfoBoardServiceImpl implements InfoBoardService{

    InfoBoardJPARepository infoBoardJPARepository;

    public InfoBoardServiceImpl(InfoBoardJPARepository infoBoardJPARepository) {
        this.infoBoardJPARepository = infoBoardJPARepository;
    }

    @Override
    public Optional<InfoBoardDTO.Find> findById(int infoId) {
        Optional<InfoBoard> infoBoardOptional = infoBoardJPARepository.findById(infoId);
        return infoBoardOptional.map(InfoBoardDTO::convertToInfoFindDTO);
    }

    @Override
    public List<InfoBoardDTO.Find> findAllByIsDeletedFalseOrderByInfoIdDesc() {
        List<InfoBoard> infoList = infoBoardJPARepository.findAllByIsDeletedFalseOrderByInfoIdDesc();
        List<InfoBoardDTO.Find> findAllNotDeletedList = new ArrayList<>();

        for(InfoBoard infoBoard : infoList) {
            InfoBoardDTO.Find result = convertToInfoFindDTO(infoBoard);
            findAllNotDeletedList.add(result);
        }
        return findAllNotDeletedList;
    }

    @Override
    @Transactional
    public void deleteById(int infoId) {
        Optional<InfoBoard> infoBoardOptional = infoBoardJPARepository.findById(infoId);
        if(infoBoardOptional.isPresent()) {
            InfoBoard infoBoard = infoBoardOptional.get();
            infoBoard.setDeleted(true);
            infoBoardJPARepository.save(infoBoard);
        }
    }

    @Override
    public void save(InfoBoard infoBoard) {
        infoBoardJPARepository.save(infoBoard);
    }

    @Override
    public void update(InfoBoard infoBoard) {
        infoBoardJPARepository.save(infoBoard);
    }
}
