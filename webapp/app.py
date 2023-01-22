from flask import Flask, jsonify # jsonify - data as JSON
import docker

app = Flask(__name__)
client = docker.from_env()

@app.route("/")
def list_containers():
    containers = client.containers.list()
    return jsonify([c.name for c in containers])

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0')