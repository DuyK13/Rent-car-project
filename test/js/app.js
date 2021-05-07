var sessions = require("client-sessions");
app.use(
    sessions({
        cookieName: "mySession", // cookie name dictates the key name added to the request object
        secret: "blargadeeblargblarg", // should be a large unguessable string
        duration: 24 * 60 * 60 * 1000, // how long the session will stay valid in ms
        activeDuration: 1000 * 60 * 5, // if expiresIn < activeDuration, the session will be extended by activeDuration milliseconds
    })
);

app.use(function (req, res, next) {
    if (req.mySession.seenyou) {
        res.setHeader("X-Seen-You", "true");
    } else {
        // setting a property will automatically cause a Set-Cookie response
        // to be sent
        req.mySession.seenyou = true;
        res.setHeader("X-Seen-You", "false");
    }
});
