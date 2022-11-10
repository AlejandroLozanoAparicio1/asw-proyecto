let title, url, body;

const changeTitle = (newTitle) => {
    title = newTitle;
}
const changeUrl = (newUrl) => {
    url = newUrl;
}
const changeBody = (newBody) => {
    body = newBody;
}

const submitNews = async () => {
    const page = url.split('/')[2];
    let jsonSubmit = {
        title: title,
        page_: page,
        username: {
            username: "alex"
        },
        link: url,
        text: body
    };
    const response = await fetch("http://localhost:8081/submit", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });

    window.location.href = 'http://localhost:8081/';
}