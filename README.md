# HangHeaStudy

## USE CASE 작성하기
![USE CASE](https://private-user-images.githubusercontent.com/52989474/391131068-496f7ff3-8ef3-484a-a8a7-392d03daa9c5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzI4OTQzMDYsIm5iZiI6MTczMjg5NDAwNiwicGF0aCI6Ii81Mjk4OTQ3NC8zOTExMzEwNjgtNDk2ZjdmZjMtOGVmMy00ODRhLWE4YTctMzkyZDAzZGFhOWM1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDExMjklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQxMTI5VDE1MjY0NlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTQ0ZDJiY2MwZGFlNmIyMGYzYWFjMDQxMjI5YTcyNjUyYzdhNmFmZDE1OGU2YjkxNmM4OGMyZmM0MGRkZTM2ZTQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.M6KfUyQmrJ8Yi2Mmd1xQMRs8_IjZBLc3fGvbDbLSNCY)

## ERD 작성하기
![ERD](https://private-user-images.githubusercontent.com/52989474/391130034-1917d93e-cfe2-4038-bb54-259c975df0b6.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzI4OTQwOTcsIm5iZiI6MTczMjg5Mzc5NywicGF0aCI6Ii81Mjk4OTQ3NC8zOTExMzAwMzQtMTkxN2Q5M2UtY2ZlMi00MDM4LWJiNTQtMjU5Yzk3NWRmMGI2LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDExMjklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQxMTI5VDE1MjMxN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWFhYzE2NDk5ZjllYmEwYzMwYjRjYzE1N2NlZWNmNTAzYjgxMTZmODU3MjczZmVkZTc4NjZiNWRkM2M1ODkyMDUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.oNI3kcEQ7k_AcFNQzHlr-JQF08vChVOjyq-wFLBfqdo)


## API 명세서 작성
[https://documenter.getpostman.com/view/14505237/2sAYBXDBoX]

## 기능 정의 사항


1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기

2. 로그인 API
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
    발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
    
1. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기

2. 게시글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
    - 제목, 작성자명(username), 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기

3. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 
    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)

4. 선택한 게시글 수정 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
    
5. 선택한 게시글 삭제 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기