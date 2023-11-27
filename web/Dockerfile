FROM node:16

WORKDIR /web

COPY package*.json ./

RUN npm install -g @angular/cli@latest
RUN npm install

COPY . .

RUN ng build --prod

EXPOSE 80

CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "80"]
