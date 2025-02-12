<div align="center">
  
# Trading simulator - BINANCE

</div>

<div align="center">
  
Project giả lập binance dựa trên react và spring boot, lấy api từ coingecko [Coingecko API](https://www.coingecko.com/). 

 

</div>

## 🔎 Preview 

![crypto_proj_1](https://private-user-images.githubusercontent.com/129868244/412332321-9b703468-24cf-4669-9cd3-a676f7054d43.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzkzNDk4NTEsIm5iZiI6MTczOTM0OTU1MSwicGF0aCI6Ii8xMjk4NjgyNDQvNDEyMzMyMzIxLTliNzAzNDY4LTI0Y2YtNDY2OS05Y2QzLWE2NzZmNzA1NGQ0My5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxMlQwODM5MTFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1jNDM1NmU0NjFlYzhhYzdhNWUyMjAxNzhkNWFjNDk2MjkyODk0NDU1YzcxOTE0MWY0MmQ1MGZiOGQxMjBmYjU0JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.YVqm9sjBa2ceLG0G2UXfxkXSU-ne7OQ2ufDmY3RXlnQ)

![crypto_proj_1](https://private-user-images.githubusercontent.com/129868244/412332922-538f88a0-470e-46b0-b374-1142a874f456.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzkzNDk4ODEsIm5iZiI6MTczOTM0OTU4MSwicGF0aCI6Ii8xMjk4NjgyNDQvNDEyMzMyOTIyLTUzOGY4OGEwLTQ3MGUtNDZiMC1iMzc0LTExNDJhODc0ZjQ1Ni5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxMlQwODM5NDFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1iZjk0N2ZjOTQ0NTRiNDA4NTNiNjU4NGYwZTZlZjYxMGVjNGM3ZWU4ODk5MDBhMmRjZWUwNTcyN2FhMGRjZDgzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.yVpBZt9kYADAFFS7BQCdFasZQFd1TFqW1CJY-sJhabQ)

![crypto_proj_1](https://private-user-images.githubusercontent.com/129868244/412333166-bed7a765-474f-41dd-b3dc-eb8bc27e7cca.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzkzNDk4OTcsIm5iZiI6MTczOTM0OTU5NywicGF0aCI6Ii8xMjk4NjgyNDQvNDEyMzMzMTY2LWJlZDdhNzY1LTQ3NGYtNDFkZC1iM2RjLWViOGJjMjdlN2NjYS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxMlQwODM5NTdaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1lOTkxODU5MGFkMWFmNzAxOGVjZDJjYzBjZDlmN2U4NDUwNWRjY2VjNTU4ZmZlYWQ4NzJkYzJlOTNkNmU3M2YwJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.EeWBkN5z3OvGtN-spPt3NXLjHaBtq3KTyRl9m0JwKgo)

![crypto_proj_1](https://private-user-images.githubusercontent.com/129868244/412333718-97f14df8-6057-4d32-820c-c66a3a074030.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzkzNDk5MTYsIm5iZiI6MTczOTM0OTYxNiwicGF0aCI6Ii8xMjk4NjgyNDQvNDEyMzMzNzE4LTk3ZjE0ZGY4LTYwNTctNGQzMi04MjBjLWM2NmEzYTA3NDAzMC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDIxMlQwODQwMTZaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hYzM1MmMwNjk2MmZjODkxMmJlMDMyMzAzODU2NzM4MjQyNDJkNjk2NjNlODlkMDFhNGY4ZDVhNDNhMWI3MjAxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.6BZR-e8JX_VcKWfY_UnHo_8HlL8DF0MAguDP07g7HOE)


## 🚀 Giới thiệu  
Trading Platform là một ứng dụng giúp theo dõi giá tiền điện tử theo thời gian thực, lấy dữ liệu từ API của CoinGecko.  

## 📌 Các tính năng  
- Xem danh sách các đồng tiền điện tử phổ biến  
- Hiển thị giá tiền tệ theo USD & VND  
- Biểu đồ thị trường theo thời gian  
- Tìm kiếm thông tin về tiền điện tử  

## 🛠️ Công nghệ sử dụng  
- **Backend:** Spring Boot, Java  
- **Frontend:** ReactJS, Tailwind CSS  
- **Database:** postgreSQL  
- **API:** CoinGecko

## ⚡ Cài đặt & Chạy dự án 
 
```bash
Clone repo
git clone https://github.com/tungnq243/trading-simulator.git
cd trading-simulator


Chạy Backend (Ưu tiên IntelliJ)
cd BinanceApiService
mvn spring-boot:run

Chạy Frontend
cd Trading-Platform/binance-client
npm install
npm run dev

Mở http://localhost:5173/




 



