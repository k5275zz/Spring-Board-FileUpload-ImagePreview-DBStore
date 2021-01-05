	$(document).ready(function(){
	var formObj=$("form[role='form']"); // 속성 선택자 form태그의 속성이 role='form'인것을 선택하라 html폼태그
	// 속성 선택자 input타입의 type='submit'인 속성값을 선택
	$("input[type='submit']").on("click",function(e){// 전송버튼을 클릭하면
		e.preventDefault();
		console.log("서브밋 버튼 클릭함.");
		var str="";
		$(".uploadResult ul li").each(function(i,obj){
			var jobj=$(obj);
			
			str+="<input type='text' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
			str+="<input type='text' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			str+="<input type='text' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		})
		//alert(str)
		formObj.append(str).submit();
		//alert("asfsafsd")
	})
	$("input[type='file']").change(function(e){

		var formData = new FormData(); // 값이 바뀔때마다 파일선택창을 띄운다
		var inputFile = $("input[name='uploadFile']"); // <input type="file" name="uploadFile" multiple="multiple">
		var files = inputFile[0].files; //파일정보가지고오기
		console.log(files.length)
		console.log(formData)
		for(var i=0;i<files.length;i++){
			console.log(files[i])
			formData.append("uploadFile",files[i]);
		}
		console.log(files);
		$.ajax({
			url:"/upload/uploadFormAction",
			data:formData,
			dataType:"json",
			// processData와 contentType은 파일업로드시 false가 되어야함.
			processData:false,
			contentType:false,
			type:'POST',
			success:function(data){
				alert("업로드됨.");
				console.log(data);
				showUploadResult(data);
			}
			
		})
	})
	// showUploadResult
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length==0){
			return;
		}
		var uploadUL=$(".uploadResult ul");
		var str="";
		$(uploadResultArr).each(function(i,obj){
			console.log("obj.uploadPath="+obj.uploadPath);
			console.log("obj.uuid="+obj.uuid);
			console.log("obj.fileName="+obj.fileName);
			var fileCallPath=encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
			str+="<li data-path='"+obj.uploadPath+"'";
			str+=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'"
			str+=" ><div>";
			str+="<span> "+obj.fileName+"</span>";
			str+="<img src='/upload/display?fileName="+fileCallPath+"'>";
			str+="</div></li>";
		})
		uploadUL.append(str);
	}
})