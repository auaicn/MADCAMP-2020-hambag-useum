<h1>공통과제 1번 (7/8 ~ 7/15)</h1>

<h2> Notes </h2>

    1. Activites & Fragments
        Activity 에는, 
        1. 기본화면을 위한 MainActivity
        2. 주소록 구현을 위한 profile_description 액티비티
        3. 권한 요청을 위한 SplashActivity
        4. 세번재 탭에서 구현한, To-do List의 항목별 세부사항을 위한 액티비티
        가 있습니다.
        
        탭 구조를 구현하기 위해서, android 에 기본 내장되어 있는 material design 의 TabLayout 을 사용하였습니다.
        ViewPager를 이용하여, ViewPagerAdapter에 추가해놓은 각 탭의 Fragment가 화면에 표시 될 수 있도록, MainActivity.java의 onCreate() 에서 처리해주었습니다.
        탭 3에서 to-do List에는 SQLite Database를 사용하였으며, 이를 위한 초기화 또한 MainActivity에서 진행하였습니다.
        
    2. About Permission
        AndroidManifest.xml 파일에 Contacts, External Storage, Direct_Dial 등 앱 실행에 필요한 권한들을 명시해 두었습니다.
![alt text](https://github.com/auaicn/common_assignment/images/permission.png "Code about permission")
        
        
<h2> 1. 첫번째 탭 </h2>
    Subject : 나의 연락처 구축
    휴대폰의 'Contacts' 어플리케이션의 contentProvider 에 query를 보내, update, read operation 을 수행하였습니다.
    query 과정에서, 연락처 content provider API를 통해 얻을 수 잇는 Starred 값으로 먼저 sorting 한후, 사전 순으로 정렬기준을 인자로 설정하였습니다.
    custom listView adapter를 구현하여, 쿼리의 결과를 listView의 각 항목으로 불러오는 과정에서, Starred 된 연락처들은 즐겨찾기 View 하나를 위에 추가하였고,
    각 연락처들의 처음으로 나오는 성을 보여주는 View를 추가하였습니다.
 ![alt text](https://github.com/auaicn/common_assignment/images/additional_view.png "Code about permission")
    
    항목을 누를경우, onItemClickListener 를 설정하여, profile_description Activity를 Intent를 사용하여 시작하도록 하였습니다.
    Intent를 통해, 항목과 관련된 세부 정보들을 받아, TextView.setText()하여 화면에 표시하였습니다.
![alt text](https://github.com/auaicn/common_assignment/images/profile_description.png "Image about profile Description")
    
    전화, 메시지, 이메일 버튼이 있으며, 누를 시 Intent를 이용하여 해당되는 기본 어플리케이션에 전화, 메시지 전송, 이메일을 보낼 수 있도록 구현하였습니다.
    
  
    
    Inline-style: 
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")
    
<h2> 1. 두번째 탭 </h2>

    Subject : 나의 갤러리 구축
    Glide를 이용하여, 이미지의 절대경로로 부터, bitmap 이미지를 읽어와서, 화면에 grid View 를 통해 표시하였습니다.
    
<h2> 1. 세번째 탭 </h2>

<h2> 1. Extras </h2>
