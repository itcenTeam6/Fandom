 fetch('/tokenverification',{
    method:'GET',
    headers:{
        'content-type':'application/json',
        'Authorization': 'Bearer '+localStorage.getItem('ACCESS_TOKEN')
    }
})