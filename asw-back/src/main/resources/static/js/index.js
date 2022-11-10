

const news = document.getElementById("news");

const getNews = async (url) => {
    const response = await fetch("http://localhost:8081/" + url);
    const json = await response.json();
    news.innerHTML = "";
    for (let i = 0; i < json.length; i++) {
        const link = json[i].type == "ask" ? "http://localhost:8081/news/" + json[i].itemId : `${json[i].link}`;
        let cssclass = "submission";
        if (i == json.length - 1) cssclass = "submission last-sub"
        if (json[i].type == "url")
        {
            news.innerHTML += `<li class="${cssclass}">
                                <div class="submission-upper">
                                    <span class="like-btn"></span>
                                    <a class="submission-title" href="${link}">${json[i].title}</a>
                                    <a class="submission-page" href="${json[i].page_}">(${json[i].page_})</a>
                                </div>
                                <div class="submission-lower">
                                    <p class="submission-info">${json[i].likedBy.length} points by <a>${json[i].username.username}</a> at ${json[i].datePublished}</p>
                                    <p class="submission-info sub-comments">${json[i].comments.length} comments</p>
                                </div>
                           </li>`;
        }
        else {
            news.innerHTML += `<li class="${cssclass}">
                                <div class="submission-upper">
                                    <span class="like-btn"></span>
                                    <a class="submission-title" href="${link}">${json[i].title}</a>
                                </div>
                                <div class="submission-lower">
                                    <p class="submission-info">${json[i].likedBy.length} points by <a>${json[i].username.username}</a> at ${json[i].datePublished}</p>
                                    <p class="submission-info sub-comments">${json[i].comments.length} comments</p>
                                    
                                </div>
                           </li>`;
        }
    }
}

getNews("news");