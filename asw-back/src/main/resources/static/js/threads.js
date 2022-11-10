let commentsection = document.getElementById("comments");

const getUserComments = async () => {
    let username = localStorage.getItem("username")
    const response = await fetch("http://localhost:8081/comment/user/" + username);
    const json = await response.json();
    commentsection.innerHTML = "";
    for (let i = 0; i < json.length; i++) {
        let cssclass = "comment";
        if (i == json.length - 1) cssclass = "comment last-sub";
            commentsection.innerHTML += `<li class="${cssclass}">
                                        <div class="comment-info">
                                            <p class="comment-points">0 points ${json[i].id}</p>
                                            <p class="comment-user"> by ${json[i].user.username}</p>
                                            <p class="comment-date"> at ${json[i].time}</p>
                                        </div>
                                        <div class="comment-body">
                                            <p class="comment-date">${json[i].body}</p>
                                        </div>
                                    </li>`;
        }
}

getUserComments();
