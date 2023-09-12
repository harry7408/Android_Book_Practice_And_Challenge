# Android_Book_Practice_And_Challenge
실무에 바로적용하는 안드로이드 4판 실습 및 챌린지</br>

 **책의 개념은 따로 적지 않고 어떻게 챌린지 문제를 해결하였는지 Readme에 작성**</br>
# GeoQuiz
>## Chapter 1
> - 챌린지 1 : Toast 커스터마이징</br>
>    \- API 30 이상부터는 Toast 메세지에 setGravity 등의 속성이 막혔다</br>
>     : 위치 변경은 고려하지 않고 FancyToast 라이브러리로 대체하여 토스트 메세지를 출력

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
> - 챌린지 1 : 허점 보완하기 -> 화면 회전 시 컨닝한 결과 사라짐&nbsp;&nbsp; \* **ViewModel, ActivityResult, 생명주기를 더 깊게 공부해야할 듯 싶다** \*</br>
  \- CheatViewModel을 만들어 buttonFlag를 두고 컨닝한 여부를 관리해 주었다. </br></br>
    화면 회전을 해서 ActivityResult에 계속 null 값이 넘어와 한참을 삽질했는데 cheatActivity의 onCreate() 부분에서 setAnswerShownResult() 함수를 호출해줘 해결할 수 있었다</br></br>
    ※ 화면 회전 후 setResult 부분이 호출되지 않고 날라갔기 때문에 계속 null이 넘어온 것 같다.<br></br>
> - 챌린지 2 : 문제마다 커닝 여부 관리하기 -> 한 문제만 커닝해도 모든 문제의 답은 커닝으로 간주한 것 해결하기</br>
  \- QuizViewModel에 isCheated 변수 선언 후 CheatActivity에서 커닝하면 registerForActivityResult의 결과로 isCheated=true로 설정 후</br>
  &nbsp;&nbsp;버튼 refresh와 마찬가지로 QuizViewModel의 isCheated 값을 refreshCheating() 함수를 통해 false로 상태를 바꿔주었다.</br></br>
    Ref) 퀴즈 점수 측정 시 커닝한 경우는 정답 횟수에 포함시키지 않았다.</br></br>
 ※ chapter 6의 startActivityForResult()는 Deprecated 되었으므로 registerForActivityResult()를 사용했음 </br>
  -> onActivityResult()를 오버라이드 하여 결과값을 가져오는데 Activity가 메모리 부족 문제 등으로 사라지면 가져올 수 없기 때문에 Deprecated 되었다<br></br>

> ## Chapter 7
> - 챌린지 1 : 장치의 안드로이드 버전 보여주기
 \- Build.VERSION.SDK_INT으로 현재 build.gradle에 있는 targetSDK 버전 숫자를 가져올 수 있다 (Build.VERSION.SDK 는 deprecated 되었다)<br></br>
> - 챌린지 2 : 커닝 횟수 제한하기
 \- cheatCount 변수를 초기에 3으로 두어 registerForActivity의 결과로 커닝한 여부를 체크하는 영역에서 cheatCount를 1 감소하여 커닝 했을때만 숫자가 감소되도록 했다.<br></br>

> ※ GeoQuiz를 마무리하며
>  - 3장의 생명주기, 4장의 ViewModel (MVVM 아키텍쳐의 중요 요소), 5장의 디버깅 부분 적어도 이 3파트의 내용은 잘 챙기기<br></br>
>  - 7장의 안드로이드 버전은 추후 개발 실력이 향상되면 조금 더 자세히 학습할 필요가 있어보인다.<br></br>
단순히 책의 실습만 하기 보다는 챌린지를 풀고 내 방식대로 바꿔가며 개발 문서도 찾고 구글링 하는 과정에서 실력이 향상된다 느꼈다. 특히 Chapter 6의 챌린지 1은 매우 시간을 오래잡아 먹어서 기억에 많이 남는다.</br>
챌린지를 다 했지만 허점이 조금 보이긴 한다 (커닝을 하고 다음문제 갔다가 다시 돌아오면 커닝 횟수는 차감되지만 커닝 여부가 사라진다) 그러나 할게 많기 때문에 이정도로 마무리 짓고 CriminalIntent로 넘어간다.<br></br>

# CriminalIntent
> ## Chapter 8
>- 챌린지 없음
>- Fragment 의 생명주기에 대한 학습 필요

> ## Chapter 9
>- 챌린지 1 : 기본으로 CrimeViewHolder를 하나 만든 후 심각한 범죄 -> PoliceViewHolder, 평범한 범죄 -> NoPoliceViewHolder에 담아 </br>
    onCreateViewHolder의 View Type에 따라 when문으로 구분하여 ViewHolder을 생성했다.
>- 자주 사용되는 RecyclerView에 대한 Chapter이다 그냥 외울 정도로 연습하는 것이 답인 것 같다.</br>
>- 이 앱부터는 챌린지 기능은 별도의 Branch에 구현할 예정이다 (책의 실습 내용과 챌린지가 겹치면 CH11에서 에러가 났다..)<br></br>

>## Chapter 10
>- 챌린지 1 : 날짜 형식을 조금 변형하여 화면에 띄우기</br>
\- DateFormat의 format 메소드에 출력할 형식과 date를 전달하거나</br> DateFormat을 상속한 SimpleDateFormat에 출력할 형식, 지역을 입력으로 생성하고 format 함수로 date를 전달하는 2가지 방법이 있다. (후자 선택)</br>
※ 이번 chapter는 처음부터 ConstraintLayout으로 화면을 구성하여 수정할 사항이 별로 없었다 <br></br>

>## Chapter 11
>- 챌린지 없음</br>
>- 자주 쓰이는 Room과 LiveData에 대한 Chapter<br></br>

>## Chapter 12
>- 챌린지 1 : RecyclerView를 ListAdapter로 상속받아 구현 (Diffutil을 활용)</br>
&nbsp;&nbsp; 컨텐츠가 같은지, 아이템 자체가 같은지 비교하는 2개의 함수 오버라이딩 하면 된다 </br>
※ 더 효율적으로 갣신하기 때문에 RecyclerView를 구현할 때 ListAdapter를 상속 받는 것이 좋아보인다<br></br>

>## Chapter 13
>- 챌린지 1 : 더 많은 대화상자 만들기</br>
\- 실습에서 구현한 것과 비슷하게 Time을 Pick 하기 위한 Fragment를 하나 만들어 onCreateDialog에서 TimePickerDialog를 반환하도록 하였다</br>
※ Dialog를 그냥 만들 수 있지만 화면 회전에 대비하여 Fragment로 만들고 FragmentManager가 관리하도록 하는 것이 좋다고 하나 이번 Chapter 실습에서 활용한 ```targetFragment 와 setTargetFragment```는 Deprecated 되었다

 
 
