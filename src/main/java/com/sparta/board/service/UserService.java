package com.sparta.board.service;

import com.sparta.board.dto.LoginRequestDto;
import com.sparta.board.dto.MessageResponseDto;
import com.sparta.board.dto.SignupRequestDto;
import com.sparta.board.entity.User;
import com.sparta.board.entity.enumSet.ErrorType;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static com.sparta.board.exception.ExceptionHandling.responseException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional
    public ResponseEntity<Object> signup(SignupRequestDto requestDto, BindingResult bindingResult) {

        // 입력한 username, password 유효성 검사 통과 못한 경우
        if (bindingResult.hasErrors()) {
            return responseException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage()); // @valid 에서 exception 발생 시, 해당 메시지를 출력한다.
        }

        // 회원 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return responseException(ErrorType.DUPLICATED_USERNAME);
        }

        // 입력한 username, password 로 user 객체 만들어 repository 에 저장
        userRepository.save(User.builder().requestsDto(requestDto).build());

        return ResponseEntity.ok(MessageResponseDto.builder()   // status : ok
                .statusCode(HttpStatus.OK.value())  // body : SuccessResponseDto (statusCode, msg)
                .msg("회원가입 성공")
                .build());

    }

    // 로그인
    @Transactional(readOnly = true)
    public ResponseEntity<Object> login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인 & 비밀번호 체크
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty() || !(user.get().getPassword().equals(password))) {
            return responseException(ErrorType.NOT_MATCHING_INFO);
        }

        // header 에 들어갈 JWT 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));

        return ResponseEntity.ok()  // status -> OK
                .headers(headers)   // headers -> JWT
                .body(MessageResponseDto.builder() // body -> SuccessResponseDto -> statusCode, msg
                        .statusCode(HttpStatus.OK.value())
                        .msg("로그인 성공")
                        .build());

    }

}
