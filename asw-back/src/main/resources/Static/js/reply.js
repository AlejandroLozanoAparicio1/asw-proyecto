let idReply = 0;

const saveId = async (id) => {
    idReply = id;
}

const addReply = async (id) => {
    let jsonSubmit = {
        user: {
            username: localStorage.getItem("username")
        },
        body: "text"
    };
    console.log(idReply);
    const response = await fetch("http://localhost:8081/news/" + idReply + "/reply", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });
    //await getNewsView(id);
}