

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

/*
*<body>
    <table>
        <tbody>
            <tr>
                <td bgcolor="#ff6600">
                    <table>
                        <tbody>
                            <tr>
                                <td style="width:18px;padding-right:4px">
                                    <a href="newest">
                                        <img src="logo.jpg" width="18" height="18" style="border:1px white solid;">
                                    </a>
                                </td>
                                <td style="line-height:12pt; height:10px;">
                                    <span class="pagetop">
                                        <b class="hnname">
                                            <a href="news">Hacker News</a>
                                        </b>
                                        <a href="/">welcome</a>
                                        <a href="newest">new</a> | <a href="threads?id=yyyyxxxx">threads</a> | <a href="front">past</a> | <a href="newcomments">comments</a> | <a href="ask">ask</a> | <a href="show">show</a> | <a href="jobs">jobs</a> | <a href="submit.html">submit</a>
                                    </span>
                                </td>
                                <td style="text-align:right;padding-right:4px;">
                                    <span class="pagetop">
                                        <a id="me" href="user?id=yyyyxxxx">yyyyxxxx</a>
                                        (<span id="karma">1</span>) | <a id="logout" href="logout?auth=260b9537da922b5c000c84056efd68a205ad0209&amp;goto=news">logout</a>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        <tr>
                <td>
                    <table>
                        <tbody id="news-container">

                        </tbody>
                    </table>
                </td>
            </tr>
        </tbody>
    </table>
</body>
*
            *
* */