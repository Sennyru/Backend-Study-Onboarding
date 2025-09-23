# 온보딩 과제

스프링부트로 구현해주세요

## 안내사항

- 뷰를 만들 필요는 없습니다.
- 예외처리는 상황에 맞게 적절히 처리해주세요. 아래는 에러 response의 예시입니다.
    
    ```json
    {
    	"time": "2023-09-10T18:49:06.5964525",
    	"status": "BAD_REQUEST",
    	"message": ".",
    	"requestURI": "/comment/1234"
    }
    
    ```
    
- 테스트 코드는 작성할 수 있는 대로 최대한 작성해주세요.
- 필요한 라이브러리는 자유롭게 쓰시면 됩니다.

## 프로젝트 세팅

- Build Tool: Gradle
- Language: Java
- Java Version: 21
- DB: H2

## 기능 요구사항

### A. 회원가입

> 비밀번호를 암호화해서 저장해주세요.
username, password에 별도의 Validation은 필요하지 않습니다.
ex) 8자 이상 금지 등
> 

**Request**

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "username" : "username"
}

```

**Response**

```json
{
    "email" : "email@urssu.com",
    "username" : "username"
}

```

### B. 게시글 작성하기

> title, content 필드는 “”, “ “, null 값을 허용하지 않습니다.
> 

**Request**

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "title" : "title",
    "content" : "content"
}

```

**Response**

```json
{
    "articleId" : 1,
    "email" : "email@urssu.com",
    "title" : "title",
    "content" : "content"
}

```

### C. 게시글 수정하기

> id 값은 Path Variable로 받습니다.title, content 필드는 “”, “ “, null 값을 허용하지 않습니다.
자신의 게시글만 수정할 수 있습니다. 
별도의 인증/로그인을 추가하지 않았다면 Request Body의 email, password와 일치하는 유저의 게시글만 수정할 수 있도록 만들어야합니다.
> 

**Request**

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "title" : "title",
    "content" : "content"
}

```

**Response**

```json
{
    "articleId" : 1,
    "email" : "email@urssu.com",
    "title" : "title",
    "content" : "content"
}

```

### D. 게시글 삭제하기

> id 값은 Path Variable로 받습니다.
해당 게시글에 있는 댓글들 또한 모두 삭제되도록 개발해주세요.
자신의 게시글만 삭제할 수 있습니다.  
별도의 인증/로그인을 추가하지 않았다면 Request Body의 email, password와 일치하는 유저의 게시글만 삭제할 수 있도록 만들어야합니다.
> 

```json
{
    "email" : "email@urssu.com",
    "password" : "password"
}

```

### E. 댓글 작성하기

> content 필드는 “”, “ “, null을 허용하지 않습니다.
게시글 연관관계는 id 정보를 통해 맺습니다.
Path Variable, Body 필드 중 적절한 방법을 선택해 받을 수 있도록 개발해주세요.
> 

**Request**

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "content" : "content"
}

```

**Response**

```json
{
    "commentId" : 1,
    "email" : "email@urssu.com",
    "content" : "content"
}

```

### F. 댓글 수정하기

> id 값은 Path Variable로 받습니다.title, content 필드는 “”, “ “, null 값을 허용하지 않습니다.
자신의 댓글만 수정할 수 있습니다. 
별도의 인증/로그인을 추가하지 않았다면 Request Body의 email, password와 일치하는 유저의 댓글만 수정할 수 있도록 만들어야합니다.
> 

**Request**

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "content" : "content"
}

```

**Response**

```json
{
    "commentId" : 1,
    "email" : "email@urssu.com",
    "content" : "content"
}

```

### G. 댓글 삭제하기

> id 값은 Path Variable로 받습니다.자신의 댓글만 삭제할 수 있습니다. 
별도의 인증/로그인을 추가하지 않았다면 Request Body의 email, password와 일치하는 유저의 댓글만 삭제할 수 있도록 만들어야합니다.
> 

```json
{
    "email" : "email@urssu.com",
    "password" : "password"
}

```

### H. 회원 탈퇴

> 회원 탈퇴 시 해당 회원이 작성한 게시글, 댓글들이 모두 삭제되도록 만들어주세요.
별도의 인증/로그인을 추가했다면 인증된 유저만 탈퇴되도록 만들어야합니다.
> 

```json
{
    "email" : "email@urssu.com",
    "password" : "password"
}

```
