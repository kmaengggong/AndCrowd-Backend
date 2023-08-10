package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.ReportDTO;
import com.fiveis.andcrowd.entity.Report;
import com.fiveis.andcrowd.repository.ReportJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    private static ReportJPARepository reportJPARepository;

    @Autowired
    public  ReportServiceImpl(ReportJPARepository reportJPARepository){
        this.reportJPARepository = reportJPARepository;
    }

    @Override
    public List<ReportDTO.Find> findAll() {
        List<Report> reportList = reportJPARepository.findAll();
        List<ReportDTO.Find> findList = new ArrayList<>();
        for(Report report : reportList){
            findList.add(convertToReportDTOFind(report));
        }
        return findList;
    }

    @Override
    public ReportDTO.Find findById(int reportId) {
        if(reportJPARepository.findById(reportId).isEmpty()) return null;
        return convertToReportDTOFind(reportJPARepository.findById(reportId).get());
    }

    @Override
    public void save(Report report) {
        reportJPARepository.save(report);
    }

    @Override
    public void update(ReportDTO.Update update) {
        Report report = reportJPARepository.findById(update.getReportId()).get();
        report.setReportStatus(update.getReportStatus());
        reportJPARepository.save(report);
    }

    @Override
    public void deleteById(int reportId) {
        reportJPARepository.deleteById(reportId);
    }

    private static ReportDTO.Find convertToReportDTOFind(Report report){
        return ReportDTO.Find.builder()
                .reportId(report.getReportId())
                .userId(report.getUserId())
                .projectId(report.getProjectId())
                .projectType(report.getProjectType())
                .reportContent(report.getReportContent())
                .reportDate(report.getReportDate())
                .reportStatus(report.getReportStatus())
                .build();
    }
}
