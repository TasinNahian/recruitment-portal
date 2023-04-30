// to generate random images
const images = ['/img-1.jpeg','/img-2.svg','/img-3.jpeg','/img-4.jpeg']
document.addEventListener('DOMContentLoaded', _ => {
  [...document.getElementsByClassName('randImg')].forEach(e => {
    const randImageIndex = ~~(Math.random() * images.length);
    e.src = images[randImageIndex];
    console.log(images)
  });
});