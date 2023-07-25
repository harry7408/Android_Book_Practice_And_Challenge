# Android_Book_Practice_And_Challenge
실무에 바로적용하는 안드로이드 4판 실습 및 챌린지</br>

 **책의 개념은 따로 적지 않고 어떻게 챌린지 문제를 해결하였는지 Readme에 작성**

>## Chapter 1
> - 챌린지 1 : Toast 커스터마이징</br>
>    \- API 30 이상부터는 Toast 메세지에 setGravity 등의 속성이 막혔다</br>
>     : 이를 FancyToast 라이브러리로 대체하여 토스트 메세지 출력

>## Chapter 2
> - 챌린지 1 : TextView에 리스너 추가하기</br>
    \- TextView도 View의 서브 클래스 이므로 onClickListener를 오버라이드 가능하여 이를 사용
> - 챌린지 2 : Previous Button 추가하기</br>
>  \- 뒤로가기 버튼을 추가하여 이전 문제로 넘어가도록 구현</br>
   : 0번 문제일 때만 주의해서 현재 Index를 마지막 문제의 Index로 변경해주었음
> - 챌린지 3 : Button을 ImageButton으로 변경하기 </br>
   \- 현업에서는 button 대신 Image를 많이 사용하므로 Vector Asset에 있는 아이콘을 이용하여 ImageView로 대체

>## Chapter 3
> - 챌린지 1 : 정답을 맞춘 문제를 건너뛰기</br>
  \- 정답을 맞춘 문제는 즉시 바로 비활성화 되도록하고 alpha 값 조정</br>
> - 챌린지 2 : 점수 보여주기
> \- 6문제에 대하여 모두 대답을 할 때 점수를 보여주도록 설정</br>
    : 문제를 양 방향으로 모두 자유롭게 움직이면 구현이 복잡해지므로 첫번째 문제에서 이전으로 가는 것은 막음 (Chapter2 챌린지 2번 수정)</br>
    -> 문제를 풀면 바로 버튼을 비활성화 시켜 6문제에 대한 답을 딱 1번씩만 하도록 구현 (Chapter3 챌린지 1 변형)

>## Chapter 4
> - 챌린지 없음
> - MVVM 아키텍쳐의 컴포넌트인 ViewModel 관련 반복 학습이 필요해 보인다
>

>## Chapter 5
> - 챌린지 1 : 프로파일러 살펴보기 (코드 작성 외의 것)
> - **디버깅 관련 Chapter로 반복학습 필요**
> - LogCat 잘 활용하기 (Error 레벨 -> fatal exception 검색)</br>
   ※ Caused by로 표시된 마지막 예외와 작성한 코드를 참조하는 스택기록의 첫번째줄 잘 살펴보기</br>
   ※ 오작동 진단을 위해서 Log.d, e 등 다양한 로그 수준의 메세지 활용 / BreakPoint 활용

>## Chapter 6
> - 챌린지 1 : 허점 보완하기 -> 화면 회전 시 컨닝한 결과 사라짐</br>
  \- 
> - 챌린지 2 : 문제마다 커닝 여부 관리하기 -> 한 문제만 커닝해도 모든 문제의 답은 커닝으로 간주한 것 해결하기</br>
  \- QuizViewModel에 isCheated 변수 선언 후 CheatActivity에서 커닝하면 registerForActivityResult의 결과로 isCheated=true로 설정 후</br>
  &nbsp;&nbsp;버튼 refresh와 마찬가지로 QuizViewModel의 isCheated 값을 refreshCheating() 함수를 통해 false로 상태를 바꿔주었다.</br></br>
    Ref) 퀴즈 점수 측정 시 커닝한 경우는 정답 횟수에 포함시키지 않았다.</br></br>
 ※ chapter 6의 startActivityForResult()는 Deprecated 되었으므로 registerForActivityResult()를 사용했음 </br>
  -> onActivityResult()를 오버라이드 하여 결과값을 가져오는데 Activity가 메모리 부족 문제 등으로 사라지면 가져올 수 없기 때문에 Deprecated 되었다
 
