FROM node:alpine as builder 
WORKDIR '/app'
COPY package.json .
RUN npm install

#Copy remaining files
COPY . .

RUN npm run build 

FROM nginx

#Copy production build files from builder phase to nginx
COPY --from=builder /app/build /usr/share/nginx/html