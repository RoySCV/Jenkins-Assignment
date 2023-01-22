FROM python:3.8-alpine
COPY webapp/requirements.txt /app/requirements.txt
WORKDIR /app
RUN pip install -r requirements.txt
COPY . /app
ENTRYPOINT [ "python" ]
CMD ["webapp/app.py" ]