/* Gallery */
var $galleryMain = $('.gallery-main');
var $galleryItem = $('.gallery-item');

$galleryItem.click(changePhoto);

function changePhoto() {
  var $this = $(this);

  /* Change images */
  var newImage = $this.css('backgroundImage');
  $galleryMain.css('backgroundImage', newImage);

  /* Change active image */
  $galleryItem.removeClass('active');
  $this.addClass('active');
}
