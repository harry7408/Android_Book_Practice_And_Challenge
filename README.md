# Android_With_Kotlin_Book
코틀린을 활용한 안드로이드 책 내용 실습</br>

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
