# java-blackjack
블랙잭 게임 미션 저장소

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 요구사항
도메인
- 카드 객체 묶음(숫자 +  종류)
- 카드(숫자 원시값 포장)
- 카드의 종류를 담당하는 enum

- 딜러(Dealer): implements Player
    - 16을 기준으로 카드를 받을지 결정하는 메서드
- 유저(User): implements Player
    - 유저에는 이름 프로퍼티가 필요하다 -> 생성자에서 이름을 받도록 변경해야 한다
    - 승 / 패
    - enum Y / N 을 기준으로 카드를 받을지 결정하는 메서드
    - 현재 받은 카드의 총 합이 21을 초과하는지 검사하는 메서드
    - 승 / 패 계산하는 메서드
- 플레이어(Player): interface
    - 카드묶음을 갖는다
    - 카드를 받는 기능
- Users: 게임에 참여한 사람의 이름으로 생성한 유저 일급 컬렉션

- 예 / 아니오 값을 갖는 enum
    - 입력한 값에 대해 유효성을 검사하는 메서드

- 카드계산 클래스
    - 플레이어의 카드 묶음을 받아서 총 합을 계산
    - 21(블랙잭)인지 판단하는 메서드
    - List<Card> 와 의존하도록 Player 를 제거한다
- 승패계산 클래스
    - 딜러와 유저의 승 / 패를 판단하는 메서드
- 승패 결과를 담는 클래스
    - 맵 자료구조로 플레이어 이름(key) - 승패결과(value) 를 담는다

DTO
- 게임에 참여할 사람의 이름을 담는 DTO
- 예 / 아니오를 담는 DTO
- 카드를 담고 있는 DTO
    - 카드묶음의 합도 포함
- 플레이어들의 상태 정보를 담는 DTO

view
InputViewer
    - 유저 이름을 입력 받을 때 중복 안되게 해야한다
    - 유저를 한 명 이상 입력해야 한다
    - 이름은 쉼표로 구분해야 한다