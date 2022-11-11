async function getUserComments(){
    let commentsection = document.getElementById("main-container");
    let username = localStorage.getItem("username")
    const response = await fetch("http://localhost:8081/comment/user/" + username);
    const json = await response.json();
    commentsection.innerHTML = "";
    let myhtml = `<div id="comments-container">
                  <ul id="comments">`;
    for (let i = 0; i < json.length; i++) {
        let cssclass = "comment";
        if (i == json.length - 1) cssclass = "comment last-sub";

        const idcomm = "com" + json[i].id;
        let hasUserComment = false;
        for (let k = 0; k < json[i].likedBy.length; ++k) {
            if (json[i].likedBy[k].username == localStorage.getItem("username"));
            hasUserComment = true;
        }
        const srcComment = hasUserComment ? "../images/heart-solid.svg" : "../images/heart-regular.svg";

        myhtml += `<div class="${cssclass}">
                                        <div class="comment-info">
                                            <img id="${idcomm}" class="like-btn" src='${srcComment}' alt="heart" onclick='likeBtn(${idcomm})' />
                                            <p class="comment-points">${json[i].likedBy.length} points </p>
                                            <p class="comment-user"> by <span>${json[i].user.username}</span> </p>
                                            <p class="comment-date"> at ${json[i].time} </p>
                                        </div>
                                        <div class="comment-body">
                                            <p class="comment-text">${json[i].body}</p>
                                        </div>
                                        <div class="comment-reply">
                                            <a class="reply-btn" onclick='goToReply(${json[i].id})' >reply</a>
                                        </div>
                                    </div>`;
    }
    myhtml += `</ul>
              </div>
            </div>`;
    commentsection.innerHTML = myhtml;
}
