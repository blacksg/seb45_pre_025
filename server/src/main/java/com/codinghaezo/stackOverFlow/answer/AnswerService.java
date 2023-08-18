package com.codinghaezo.stackOverFlow.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;


    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId) {
        return answerRepository.findById(answerId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswers(long Id) {
        return answerRepository.findByQuestionId(Id);
    }


   /* public Page<Answer> findAll(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page - 1, size, Sort.by("answerId").descending()));
    } */

    /*   여기도 특정사용자가 작성한 답변 전체조회하는 부분
          public List<Answer> findAnswersByMemberIdAndQuestionId(Long memberId, Long questionId) {
          return answerRepository.findByMember_IdAndQuestion_Id(memberId, questionId);
      }
     */
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(long answerId) {
        Answer foundAnswer = findAnswer(answerId);
        answerRepository.delete(foundAnswer);
    }

    public Answer updateAnswer(Answer answer, long answerId) {
        Answer foundAnswer = findAnswer(answerId);
        foundAnswer.setContent(answer.getContent());
        return answerRepository.save(foundAnswer);
    }

    public Answer updateAnswer(Answer answer, long answerId, String membername) {
        Answer foundAnswer = findAnswer(answerId);
        //membername 이랑 이름이랑 비교해야함 수정해야함
        if (membername == foundAnswer.getContent()) {
            foundAnswer.setContent(answer.getContent());
            return answerRepository.save(foundAnswer);
        } else {
            return null;
        }


    }
}




