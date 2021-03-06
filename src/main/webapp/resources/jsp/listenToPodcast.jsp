<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listen</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/audioPlayer.css"
	type="text/css">
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript" src="resources/bower_components/jPlayer/dist/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="resources/js/audioPlayer.js"></script>
</head>
<body>
<div id="header">
		<div>
			<ul id="navigation">
				<li><a href="home.action">Home</a></li>
				<li><a href="podcast.action">Podcasts</a></li>
				<li><a href="logout.action">Log Out</a></li>
				<li><a href="createRSSFeed.action">Create RSS Feed</a></li>
				<li><a href="navigateToUploadPodcast.action">Upload</a></li>
			</ul>
		</div>
	</div>
	<h1>TESTING</h1>
	<div id="jquery_jplayer_1" class="jp-jplayer" filePath="${episodePath}"></div>

	<div id="jp_container_1" class="jp-audio">
		<div class="jp-type-single">

			<div class="jp-title">
				<ul>
					<li><p>Episode Name: ${episodeName}</p></li>
					<li><p>Path: ${episodePath}</p></li>
				</ul>
			</div>

			<div class="jp-gui jp-interface">

				<ul class="jp-controls">
					<li><a href="javascript:;" class="jp-play" tabindex="1">&#61515;</a></li>
					<li><a href="javascript:;" class="jp-pause" tabindex="1">&#61516;</a></li>
					<li><a href="javascript:;" class="jp-mute" tabindex="1"
						title="mute">&#61480;</a></li>
					<li><a href="javascript:;" class="jp-unmute" tabindex="1"
						title="unmute">&#61478;</a></li>
				</ul>

				<div class="jp-progress">
					<div class="jp-seek-bar">
						<div class="jp-play-bar"></div>
					</div>
				</div>

				<div class="jp-time-holder">
					<div class="jp-current-time"></div>
				</div>

				<div class="jp-volume-bar">
					<div class="jp-volume-bar-value"></div>
				</div>

				<div class="jp-no-solution">
					<span>Update Required</span> To play the media you will need to
					either update your browser to a recent version or update your <a
						href="http://get.adobe.com/flashplayer/" target="_blank">Flash
						plugin</a>.
				</div>
			</div>
		</div>
	</div>
	<br>
	<!-- can't use href because href is only for get. don't worry too much as this will all be reworked when the backend is nearing completion -->
	<form action="tip.action?podcastID=${podcastID}" method="post">
		<input type="submit" value="Tip 1 XRP"/>
	</form>
</body>
</html>