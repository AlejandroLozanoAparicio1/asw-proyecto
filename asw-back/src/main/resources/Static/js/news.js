const news = document.getElementById("news-container");
let newComment = "";

const changeNewComment = (NewComment) => {
    newComment = NewComment;
}

const addComment = async (id) => {
    let jsonSubmit = {
        user: {
            username: "alex"
        },
        body: newComment
    };
    const response = await fetch("http://localhost:8081/news/" + id + "/newcomment", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });
    await getNewsView(id);
}

const getNewsView = async (id) => {
    const response = await fetch("http://localhost:8081/news/" + id);
    const json = await response.json();
    news.innerHTML = "";
    let myhtml = `<div class="news-wrapper">`;
    const link = json.type == "ask" ? "http://localhost:8081/news/" + json.itemId : `${json.link}`;
    if (json.type == "url") {
        myhtml += `<div class="news-upper">
                        <span class="like-btn"></span>
                        <a class="news-title" href="${link}">${json.title}</a>
                        <a class="news-page" href="${json.page_}">(${json.page_})</a>
                   </div>
                   <div class="news-lower">
                        <p class="news-info">${json.likedBy.length} points by <a>${json.username.username}</a> at ${json.datePublished}</p>
                        <p class="news-info news-comments">${json.comments.length} comments</p>
                   </div>
                   <div class="news-body">
                        ${json.text}
                   </div>
                   <div class="comment-form">
                        <form>
                            <input type="text" onchange="changeNewComment(value)" />
                            <input type="button" value="Add comment" class="add-comm-btn"  onclick="addComment(${id})"/>
                        </form>
                   </div>
                   <div class="news-comments">`;
    } else {
        myhtml += `
                                <div class="news-upper">
                                    <span class="like-btn"></span>
                                    <a class="news-title" href="${link}">${json.title}</a>
                                </div>
                                <div class="news-lower">
                                    <p class="news-info">${json.likedBy.length} points by <a>${json.username.username}</a> at ${json.datePublished}</p>
                                    <p class="news-info">${json.comments.length} comments</p>
                                </div>
                                <div class="news-body">
                                    ${json.text}
                               </div>
                               <div class="comment-form">
                                    <form>
                                        <input type="text" onchange="changeNewComment(value)" />
                                        <input type="button" value="Add comment" class="add-comm-btn"  onclick="addComment(${id})"/>
                                    </form>
                               </div>
                               <div class="news-comments">`;
    }
    for (let i = 0; i < json.comments.length; ++i) {
        let cssclass = "comment";
        if (i == json.comments.length - 1) cssclass = "comment last-sub";
        myhtml += `<div class="${cssclass}">
                                        <div class="comment-info">
                                            <span class="ini-comment"></span>
                                            <p class="comment-points">0 points </p>
                                            <p class="comment-user"> by <span>${json.comments[i].user.username}</span> </p>
                                            <p class="comment-date"> at ${json.comments[i].time} </p>
                                        </div>
                                        <div class="comment-body">
                                            <p class="comment-text">${json.comments[i].body}</p>
                                        </div>
                                    </div>`;
    }
    myhtml += `</div></div>`;
    news.innerHTML = myhtml;
}

getNewsView(54);