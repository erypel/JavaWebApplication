/**
 * testing that js is working with spring mvc
 */

function doSomething() {
	alert("js is working in spring mvc")
}

//create the player
var wavesurfer = WaveSurfer.create({
	container: '#waveform',
    scrollParent: true
});

//load the audio file
wavesurfer.load('\\JavaWebApplication\\uploads\\testWav.wav');

//when ready, play
wavesurfer.on('ready', function () {
    wavesurfer.play();
});