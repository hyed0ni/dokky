/**
 * 
 */
 
  var limitByte = 1000;
  var totalByte = 0;

  const fncheckByte = (obj)=>{			// 바이트 수 계산해주는 함수
      totalByte = 0;
      var cmtMessage = $(obj).val();
      for(var i =0; i < cmtMessage.length; i++) {
              var currentByte = cmtMessage.charCodeAt(i);
              if(currentByte > 128){
              	totalByte += 3;
              }else {
              	totalByte++;
              }
      }
      $("#messagebyte").text(totalByte);
  };  
  
  var sessionUser = document.getElementById('hidden-userInfo').value;
  
  function getBoardNoFromURL() {
	    var urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get('boardNo');	// 요청 파라미터에서 boardNo 를 찾아서 값을 리턴해줌
	}
	var boardNo = getBoardNoFromURL();	// 이전 페이지에서 보드 넘버 받아온거 
	
	const fnAddBoardHit = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/putBoardHit',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
			},
			error:function(jqXHR){ 
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}			
		})
	}
	
	const fnShowDetailBoard = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoardByNo',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
				fnAddBoardHit();
				document.getElementById('contents-writer').innerHTML = data.user.userName;
				document.getElementById('detail-title').innerHTML = data.boardTitle;
				document.getElementById('detail-contents').innerHTML = data.boardContent;
				document.getElementById('create-dt').innerHTML = moment(data.boardCreateDt).format('YYYY.MM.DD HH:mm');
				document.getElementById('board-hit').innerHTML = data.boardHit;
				
				let img = '';
				if(data.user.userUploadPath == null && data.user.userImg == null)
					img = '<img src="/images/dokky_profile.png" id="img-radius">';
				else
					img = '<img src="' + data.user.userUploadPath + data.user.userImg + '" id="img-radius">';
					
				$('.image-writer').append(img);
				if(sessionUser == data.user.userName)
				{
					let str = '<div class="contents-button">';
					str += '<button type="button" class="btn btn-primary" id="btn-modify">수정</button>';
					str += '<button type="button" class="btn btn-danger" id="btn-delete">삭제</button>';
					str += '</div>';
					$('#detail-contentinfo').append(str);
				  	fnClickDelete();
				  	fnClickModify();
				}
			},
			error:function(jqXHR){
				alert("디테일 에러");
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}
	
	const fnClickDelete = ()=> {
		document.getElementById('btn-delete').addEventListener('click', function(evt){
			if(confirm("정말 게시글을 삭제할까요?"))
			{
				$.ajax({
					type:'DELETE',
					url: '/dokky/deleteBoard/' + boardNo,
					dataType:'json',
					success: function(data){
						location.href = "/dokky/main";
					},
					error:function(jqXHR){
						alert(jqXHR.statusText + '(' + jqXHR.status + ')');
					}
				})
			}
		})
	}
	
	const fnClickModify = () => {
		
		document.getElementById('btn-modify').addEventListener('click', function(evt) {
			window.location.href = '/dokky/modify?boardNo=' + boardNo;
		})
	}
	
	const fngetHotBoard = ()=>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoard',
			dataType : 'json',
			success : function(data) 
			{
				console.log(data.length);
				for(var i = 1; i < 4; i++)
				{
					data.sort((a,b) => b.boardHit - a.boardHit);
	 		        let str = '<div style="text-align:left;">' 
	 		        + '<a class="text-gray-400 hover:text-blue-500 dark:hover:text-blue-200" href="/dokky/detail?boardNo=' 
	 		        		+ data[i-1].boardNo+ '" style="font-size:1.5rem;">'
	 		        + '-' + data[i - 1].boardTitle + '</a>' + '</div>';
	 		        $('#hot-boardList').append(str);
				}
			},
			error:function(jqXHR)
			{
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}
	
  	const fnRegistComment = ()=>{
  		document.getElementById('btn-comment').addEventListener('click', function(){
  			if(sessionUser == '')
  			{
  				alert('댓글을 작성하려면 로그인 해야합니다.');
				window.location.href = "/dokky/signin";				
			}
  			else
			{
  				if(totalByte > 1000)
  					alert('1000byte가 넘으면 등록이 안됩니다.');
  				else if($.trim($('#comment-box').val()) === ''){
  					alert('내용이 없으면 등록이 안됩니다.');
  				}
  				else
				{
		  			$.ajax({
		  				type:'POST',
		  				url: '/detail/registCmt',
		  				contentType:'application/json',
		  				data: JSON.stringify({
		  					'commentContent' : $('#comment-box').val(),
		  					'userNo' : $('#userNo').val(),
		  					'boardNo' : boardNo
		  				}),
		  				dataType: 'json',
		  				success:(data)=>{
							$('#comment-box').val('');
							location.reload();
		  				},
		  				error:(jqXHR)=>{
		  					console.log($('#comment-box').val());
		  					alert(jqXHR.statusText + '(' + jqXHR.status + ')');  					
		  				}
		  			})
				}
			}
  		})
  	}
  	
  	const fnShowCommentList = ()=>{
		$.ajax({
			type:'GET',
			url: '/detail/getCmt/' + boardNo,
			dataType : 'json',
			success : function(data) 
			{
				for(var i = 0; i < data.length; i++)
				{
	 		        let str = '<ol>';
	 		        if(data[i].user.userUploadPath == null && data[i].user.userImg == null)
	 		        	str += '<div class="image-commenter" ><img src="/images/dokky_profile.png" width="30" height="30" id="img-radius">';
 		        	else 
	 		        	str += '<div class="image-commenter" ><img src="' + data[i].user.userUploadPath + data[i].user.userImg + '" width="30" height="30" id="img-radius">';
	 		        	 
					str += '<a class="comment-writer" id="comment-writer">' + data[i].user.userName + '&nbsp;</a>';
					str += '<i class="fa-regular fa-clock"></i>&nbsp;<span id="create-dt">' + data[i].cmtCreateDt + '</span>';
					str += '<input type="hidden" value="' + i + '">';
					if(sessionUser == data[i].user.userName)
					{
						str += '<div class="comment-buttons">';
						str += '<button type="button" class="btn-comment-modify" id="btn-comment-modify' + i +'" data-commentNo="'
								+ data[i].commentNo +'">수정</button>';
						str += '<button type="button" class="btn-comment-remove" id="btn-comment-remove' + i +'" data-commentNo="'
								+ data[i].commentNo +'">삭제</button>';
						str += '</div>';
						str += '</div>';
						str += '<p class="comment-text" id="comment-text' + i +'">' 
								+ data[i].commentContent.replace(/(?:\r\n|\r|\n)/g,'<br/>');
						str += '<hr style="border:solid 1px; margin-top:10px;"></ol>';	
		 		        $('#comment-list').append(str);
		 		       	fnRemoveComment(i);
		 		       	fnModifyComment(i, data[i].commentContent, data[i].commentNo);
					}
					else
					{
						str += '</div>';
						str += '<div class="comment-text" id="comment-text' + i +'">' + data[i].commentContent + '</div>'; 
						str += '<hr style="border:solid 1px; margin-top:10px;"></ol>';	
		 		        $('#comment-list').append(str);						
					}
				}
			},
			error:function(jqXHR)
			{
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})  
  	}
  	
  	const fnRemoveComment = (evt)=>{
       	var removeButton = document.getElementById('btn-comment-remove' + evt + '');
	      	removeButton.addEventListener('click', function() {
	        var commentNo = $(this).data('commentno');
	        if(confirm("댓글을 삭제하시겠습니까?"))
	        {
				$.ajax({
					type:'DELETE',
					url:'/detail/deleteCmt/' + commentNo,
					dataType:'json',
					success: function(data){
						location.reload();
					},
					error:function(jqXHR){
						alert(jqXHR.statusText + '(' + jqXHR.status + ')');
					}
				})	
			}
      });  		
  	}
  	
  	const fnModifyComment = (evt, evt2, evt3)=>{
       	var modifyButton = document.getElementById('btn-comment-modify' + evt + '');
       		modifyButton.addEventListener('click', function() {
			var commentText = document.getElementById('comment-text' + evt + '');
			commentText.textContent = "";
			let str = '<textarea rows="5" cols="75" id="commentupdate-box">'+ evt2 +'</textarea>';
			str += '<div class="modifybycomment">';
			str += '<button type="button" class="btn btn-danger" id="btn-comment-updatecancle">취소</button>';
			str += '<button type="button" class="btn btn-primary" id="btn-comment-update">등록</button>';
			str += '</div>';
			$(commentText).append(str);
			
			document.getElementById('btn-comment-updatecancle').addEventListener('click', function(){
				commentText.textContent = "";
				$(commentText).append(evt2);
			})
			
			document.getElementById('btn-comment-update').addEventListener('click', function(){
				if($.trim($('#commentupdate-box').val()) === '')
					alert('수정할 내용을 입력해야 합니다.');
				else
				{
					$.ajax({
						type:'POST',
						url:'/detail/modifyCmt',
		 				contentType:'application/json',
		  				data: JSON.stringify({
		  					'commentContent' : $('#commentupdate-box').val(),
		  					'userNo' : evt3,
		  					'boardNo' : evt3
		  				}),
		  				dataType: 'json',
		  				success:(data)=>{
							$('#commentupdate-box').val('');
							location.reload();
		  				},
		  				error:(jqXHR)=>{
		  					console.log($('#comment-box').val());
		  					alert(jqXHR.statusText + '(' + jqXHR.status + ')');  					
		  				}
					})
				}
			})
      });  		
  	}
  	
  	fnShowCommentList();
  	fnRegistComment();
  	fngetHotBoard();	
    fnShowDetailBoard();