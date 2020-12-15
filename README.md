# Spring-Board-FileUpload-ImagePreview-DBStore
<br>
<h2>파일 업로드</h2><br>
<b>게시판+파일업로드</b>
<ul>
  <li>파일등록 및 이미지파일 미리보기 기능 구현</li>
  <li>submit(등록버튼) 클릭 시 컨트롤러를 지나 서비스에서 파일업로드+게시판정보를 트랜잭션을 이용하여 DB로직을 동시에 처리합니다. </li>
</ul>
<hr>

![image](https://user-images.githubusercontent.com/71121027/102063858-eae70a00-3e39-11eb-868d-ef9dd18a28ba.png)

<hr>

![image](https://user-images.githubusercontent.com/71121027/102064139-4d400a80-3e3a-11eb-8b27-5c5b5588fdd5.png)

<hr>

<h2>특이사항</h2><br>

<b>트랜잭션을 이용하여 두개의 정보가 동시에 들어갈때만 실행되게 한다.</b>


![image](https://user-images.githubusercontent.com/71121027/102066671-701fee00-3e3d-11eb-98b2-40e53a99bfda.png)

<hr>

![image](https://user-images.githubusercontent.com/71121027/102066549-4d8dd500-3e3d-11eb-9583-5e720635233c.png)


<hr>
<h2>DB</h2><br>

![image](https://user-images.githubusercontent.com/71121027/102066282-f6880000-3e3c-11eb-8d39-901e67e9306d.png)

<hr>

![image](https://user-images.githubusercontent.com/71121027/102066404-1fa89080-3e3d-11eb-903d-f1b5375514bf.png)
