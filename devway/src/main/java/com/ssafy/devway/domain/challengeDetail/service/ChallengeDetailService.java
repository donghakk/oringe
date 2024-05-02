package com.ssafy.devway.domain.challengeDetail.service;

import com.ssafy.devway.domain.challengeDetail.document.ChallengeDetail;
import com.ssafy.devway.domain.challengeDetail.dto.request.ChallengeDetailReqDto;
import com.ssafy.devway.domain.challengeDetail.repository.ChallengeDetailRepository;
import com.ssafy.devway.global.config.autoIncrementSequence.service.AutoIncrementSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeDetailService {

    private final ChallengeDetailRepository challengeDetailRepository;
    private final AutoIncrementSequenceService autoIncrementSequenceService;

    /*
     * 3.1 챌린지 순서 생성
     * */
    public Long insertChallengeDetail(ChallengeDetailReqDto dto) {
        ChallengeDetail challengeDetail = ChallengeDetail.builder()
            .challengeDetailId(
                autoIncrementSequenceService.generateSequence(ChallengeDetail.SEQUENCE_NAME))
            .challengeDetailTitle(dto.getChallengeDetailTitle())
            .challengeDetailContent(dto.getChallengeDetailContent())
            .challengeDetailImage(dto.getChallengeDetailImage())
            .challengeDetailImageContent(dto.getChallengeDetailImageContent())
            .challengeDetailVideo(dto.getChallengeDetailVideo())
            .Digital(dto.getChallengeDetailAppName())
            .Call(dto.getChallengeDetailAppTime())
            .WakeUp(dto.getChallengeDetailCallName())
            .Walk(dto.getChallengeDetailCallNumber())
            .build();

        challengeDetailRepository.save(challengeDetail);

        return challengeDetail.getChallengeDetailId();
    }

}
