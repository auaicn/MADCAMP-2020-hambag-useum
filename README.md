<h1>공통과제 1번 (7/8 ~ 7/15)</h1>  
<h2> Notes </h2>
<h3> 1. Activites & Fragments  </h3>
  
Activity 에는,  
1. 권한 요청을 위한 SplashActivity
2. 기본화면을 위한 MainActivity
3. 첫번째 탭 주소록 구현을 위한 profile_description 액티비티
4. 세번재 탭에서 구현한, To-do List의 항목별 세부사항을 위한 액티비티
가 있습니다.
  
탭 구조를 구현하기 위해서, android 에 기본 내장되어 있는 `material design` 의 TabLayout 을 사용하였습니다.
`ViewPager`를 이용하여, ViewPagerAdapter에 추가해놓은 각 탭의 Fragment가 화면에 표시 될 수 있도록, MainActivity.java의 onCreate() 에서 처리해주었습니다.  
탭 3에서 to-do List에는 SQLite Database를 사용하였으며, 이를 위한 초기화 또한 MainActivity에서 진행하였습니다.
  
추가로, 연락처 세부정보에서, 연락처의 편집을 위한 Fragment가 있습니다.  
  
<h3> 2. About Permission  </h3> 
  
    AndroidManifest.xml 파일에 Contacts, External Storage, Direct_Dial 등 앱 실행에 필요한 권한들을 명시해 두었습니다.  
![Alt text](https://github.com/auaicn/common_assignment/blob/master/images/permission.png)  
    실제 권한의 요청이 일어나는 부분은 SplashFragment에 구현이 되어 있습니다.   
    MainActivity에 들어가기 이전, SplashFragment 에서 명시된 모든 권한을 요청합니다.  
    권한과 관련된 모든 요청을 확인 받은 경우에만 어플리케이션을 실행하며, 하나라도 거부되는 경우, 어플리케이션을 종료합니다.  
 ![Alt text](https://github.com/auaicn/common_assignment/blob/master/images/permission_array.png)  
    SplashActivity는 여러 handler 작동이 성공적으로 실행될 경우, MainActivity를 시작하고, 종료됩니다.  
        
<h2> 1. 첫번째 탭 </h2>  

Subject : 나의 연락처 구축  

휴대폰의 'Contacts' 어플리케이션의 contentProvider 에 query를 보내, update, read operation 을 수행하였습니다.  
query 과정에서, 연락처 content provider API를 통해 얻을 수 잇는 `Starred` 값으로 먼저 sorting 한후, 사전 순으로 정렬기준을 인자로 설정하였습니다.  
`custom listView adapter`를 구현하여, 쿼리의 결과를 listView의 각 항목으로 불러오는 과정에서, Starred 된 연락처들은 즐겨찾기 View 하나를 위에 추가하였고,  
각 연락처들의 처음으로 나오는 성을 보여주는 View를 추가하였습니다.  
![Alt text](https://github.com/auaicn/common_assignment/blob/master/images/additional_view.png)  

항목을 누를경우, onItemClickListener 를 설정하여, profile_description Activity를 Intent를 사용하여 시작하도록 하였습니다.  
Intent를 통해, 항목과 관련된 세부 정보들을 받아, TextView.setText()하여 화면에 표시하였습니다.  
![Alt text](https://github.com/auaicn/common_assignment/blob/master/images/profile_description.png)  

`TextWatcher`를 사용하여, 연락처 검색과, 실시간 결과 반환이 가능하도록 구현하였습니다.  
![Alt text](https://github.com/auaicn/common_assignment/blob/master/images/search_example.png)  

전화, 메시지, 이메일 버튼이 있으며, 누를 시 Intent를 이용하여 해당되는 기본 어플리케이션으로 하여금 전화, 메시지 전송, 이메일 기능을 수행할 수 있도록 구현하였습니다.
이름 옆의 별 모양과, 가장 하단의 "즐겨찾기에 추가" 버튼을 누르면, 연락처 목록에서 상단에 위치할 수 있도록 하였습니다.

<h2> 2. 두번째 탭 </h2>

Subject : 나의 갤러리 구축

구글에서 공개한 이미지 라이브러리인 `Glide`를 이용하여 기기의 `Environment class`의 변수값으로 부터 사진이 있는 directory 정보를 얻어내고,  
해당 디렉토리 내 모든 이미지의 URI를 구하여, glide 를통해, image View에 mapping 하였습니다.  
각 사진의 image View를 gridView adapter를 통해, grid View의 항목으로 설정하여 화면에 표시되도록 구현하였습니다.  
layout 의 visibility을 활용하여 gridView.onItemClickListener 설정 내부에서, 큰 사진을 화면에 표시할 수 있도록 하였습니다.  

<h2> 3. 세번째 탭 </h2>

Subject : 자유 주제

To-do List를 구현하였습니다.



<h2> 1. Extras </h2>
  
어플리케이션 아이콘, 처음 실행시의 애니메이션, 전체적인 어플리케이션 내부 색상을 맞추기 위하여, resource,values folder에 `colors.xml`, `styles,xm`l 파일 내용을 변경하였으며,  
textview를 상속하는 여러 view들의 글꼴을 맞추기 위하여 font 파일들을 `resource/font` directory에 추가하였습니다.  
