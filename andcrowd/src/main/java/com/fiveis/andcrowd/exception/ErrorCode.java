package com.fiveis.andcrowd.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    /**
     *  회원가입 로직 예외
     */
    DUPLICATED_USERID(HttpStatus.CONFLICT,"이미 존재하는 아이디 입니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다."),
    /**
     *  로그인 로직 예외
     */
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 잘못되었습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    /**
     *  모임관련 로직 예외
     */
    CREW_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 모임이 없습니다."),
    LIMIT_NUMBER_EXCEED(HttpStatus.CONFLICT,"모임 인원수를 초과하였습니다."),
    /**
     * 접근 권한 예외
     */
    FORBIDDEN_REQUEST(HttpStatus.FORBIDDEN, "ADMIN 회원만 접근할 수 있습니다."),
    /**
     * 그 외
     */
    WRONG_PATH(HttpStatus.UNAUTHORIZED,"잘못된 경로 입니다."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "회원가입 후 진행가능합니다."),
    INVALID_PHOTO_FORM(HttpStatus.BAD_REQUEST, "이미지 형식이 맞지 않습니다."),
    FILE_CONVERSION_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 변환하면서 문제가 발생했습니다."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글이 없습니다."),
    DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러입니다."),
    // 수정 시 일치하지 않는 작성자일경우 사용
    NOT_MATCH(HttpStatus.NOT_FOUND, "작성자가 아닙니다."),

    NOT_ALLOWED_PARTICIPATION(HttpStatus.BAD_REQUEST,"참여인원이 가득찼습니다."),

    NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    NOT_FOUND_PARTICIPATION(HttpStatus.NOT_FOUND, "사용자가 모임에 참석해 있지 않습니다.")
    ;
    private HttpStatus httpStatus;
    private String message;
}
