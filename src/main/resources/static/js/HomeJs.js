async function HomeCheckLists(){
    const response = await fetch("/home", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    });
    const checkLists = await response.json();
}