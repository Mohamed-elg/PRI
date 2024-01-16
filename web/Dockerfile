FROM node:21-alpine3.18

WORKDIR /app

COPY package*.json ./

COPY . /app

RUN npm install

RUN npm run build

EXPOSE 8082

CMD ["node", "."]