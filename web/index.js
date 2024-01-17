const express = require("express");
const path = require("path");

const app = express();

app.use(express.static(path.join(__dirname, "dist/web")));

app.get("/config", (req, res) => {
  res.json({ URL_API: "http://api:8081" });
});

app.get("/*", function (req, res) {
  res.sendFile(path.join(__dirname, "dist/web/index.html"));
});

console.log("Running on port 8082");
app.listen(8082);
