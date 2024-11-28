

//Bookmark AJAX
document.querySelectorAll(".bookmark").forEach(bookmark=> bookmark.addEventListener("click",function(){
	const articleID=this.getAttribute("data-article-id")
	console.log(articleID)
	fetch(`/user/bookmark/${articleID}`,{
		method:'POST',
		headers:{
			'Content-Type':'application/json',
		},
	}).then(response => response.json())
	.then(data => console.log('Success',data))
	.catch(error=>{
		console.error('Error: ',error)
	})
}))

function deleteBookmark(article_id){
	console.log(article_id+'pressed')
	fetch(`/user/bookmark/${article_id}`,{
		method:'DELETE'
	})
	.then(response => {
		if(response.ok){
			const bookmarkElement=document.getElementById(`bookmark-${bookmarkId}`);
			bookmarkElement.remove();
		}
	})
	.catch(error => {console.error('Error:',error)})
}
