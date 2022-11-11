let idReply = 0;
let replyText = "";

const handleChangeReply = (text) => {
    replyText = text;
}


const saveId = async (id) => {
    idReply = id;
}

const addReply = async (id) => {
    let jsonSubmit = {
        user: {
            username: localStorage.getItem("username")
        },
        body: replyText
    };

    const response = await fetch("http://localhost:8081/news/" + idReply + "/reply", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });
    //await getNewsView(id);
}