//set the media element for the audio player
$(document).ready(function(){
  $("#jquery_jplayer_1").jPlayer({
    ready: function () {
      $(this).jPlayer("setMedia", {
        mp3: document.getElementById('jquery_jplayer_1').getAttribute("filePath")
      });
    },
    swfPath: "/js",
    supplied: "mp3,oga"
  });
});