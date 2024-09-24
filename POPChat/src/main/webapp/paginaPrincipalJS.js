function showPopup(){
    document.getElementById('popup').style.display='block';
}

function hidePopup(){
    document.getElementById('popup').style.display='none';
}

window.addEventListener('load',showPopup);

document.getElementById('newChat').addEventListener('click', showPopup);

document.querySelectorAll('.pop-categoria button').forEach(button => {
    button.addEventListener('click', hidePopup);
});

window.addEventListener('click',function(event){
    if(event.target === this.document.getElementById('popup')){
        hidePopup();
    }
});


