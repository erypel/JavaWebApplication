$(document).ready(function(){
  $("#jquery_jplayer_1").jPlayer({
    ready: function () {
      $(this).jPlayer("setMedia", {
        mp3: "testmp3.mp3"
      });
    },
    swfPath: "/js",
    supplied: "mp3,oga"
  });
});