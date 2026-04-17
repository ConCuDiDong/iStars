## Các lệnh cơ bản khi dùng Git (với GitHub) và cmd (Terminal):

## 1. Tình huống giả định:

**Bạn đã cài phần mềm Git, có tài khoản GitHub cá nhân và chưa có dự án (Project) từ GitHub vào trong máy tính cá nhân của bạn.**

## 2. Các lệnh cơ bản để thao tác với cmd (Terminal):
   
  2.1. Lệnh để di chuyển vào 1 thư mục đã có sẵn trong máy (bạn đang ở thư mục mẹ):

  Giả sử tôi có thư mục **iStars-store-software** ở ngoài màn hình nền của máy, tôi sẽ ấn phải chuột ở ngoài màn hình nền, chọn **Git Bash Here** và nhập lệnh sau:

  ```cmd
  C:\Users\Dra\Desktop> cd iStars-store-software
  ```

  Trong đó: `cd` là lệnh để di chuyển, **iStars-store-software** là tên thư mục mà tôi đã có sẵn ở ngoài màn hình nền trong máy của tôi

  2.2. Lệnh để di chuyển từ thư mục con ra ngoài thư mục mẹ

  Giả sử tôi đang ở trong thư mục **entity** là 1 thư mục nằm ở trong thư mục **iStars-store-software** là thư mục mẹ (thư mục gốc), thì để di chuyển từ thư mục **entity** ra thư mục mẹ bằng cmd (Terminal), tôi sẽ gõ lệnh sau:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software\entity> cd ..
  ```

  Trong đó: `cd` là lệnh để di chuyển, `..` là để lùi về phía trước 1 thư mục (tức là bạn đang đứng ở thư mục **entity**, lùi về phía trước 1 thư mục thì nó sẽ là thư mục mẹ tên là **iStars-store-software**)
  Sau khi dùng lệnh trên để di chuyển lùi về trước, cmd (Terminal) phải được thay đổi thành:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software\entity> cd ..
  C:\Users\Dra\Desktop\iStars-store-software>
  ```

  2.3. Lệnh để xoá file:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> del {tên_file_cần_xoá}
  ```

  Lưu ý: Hãy chú ý đường dẫn file để xoá chính xác file cần xoá, ví dụ xoá file **DienThoai.java** trong thư mục **entity** nằm trong thư mục mẹ là **iStars-store-software**:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> del entity\DienThoai.java
  ```

## 3. Các lệnh thao tác với Git
   
  3.1. Lệnh để tải toàn bộ dự án về máy (gọi là **clone về Local**)

  Tại cửa sổ lệnh, bạn nhập theo mẫu cú pháp sau:

  ```
  C:\Users\Dra\Desktop> git clone https://github.com/{tên_người_tạo_project}/{tên_project} --branch {branch của dự án đó như main, test, demo,...}
  ```

  Trong đó:
  `--branch` là lệnh tùy chọn, **nếu không có thì Git sẽ tự động chọn branch mặc định trên GitHub làm branch chính trong máy**

  Ví dụ tôi muốn tải dự án **iStars-store-software** về máy với branch là **main**, đặt tại màn hình nền của máy tính thì tôi sẽ ấn phải chuột ở ngoài màn hình nền, chọn **Git Bash Here** và nhập lệnh sau:

  ```
  C:\Users\Dra\Desktop> git clone https://github.com/dragonx943/iStars-store-software --branch main
  ```

  **Lưu ý: Với các câu lệnh git clone, git sẽ không phân biệt chữ hoa chữ thường !!!**

  3.2. Lệnh để làm mới lại kho lưu trữ (**gọi là Repository / Repo**) và cập nhật các thay đổi mới từ trên GitHub về máy:

  ```
  C:\Users\Dra\Desktop> git pull
  ```

  **Lưu ý: Trong một số trường hợp, file code bạn đã sửa và trên GitHub cũng có người đã sửa file đó và đẩy lên kho lưu trữ (Repo) thì sẽ gây ra lỗi conflict, nên hãy đảm bảo rằng code của mình đã được tải lên (push) trước khi pull về dữ liệu mới !!!**

  3.3. Đẩy code đã sửa lên kho lưu trữ

  **Cần phải thực thi 3 câu lệnh sau để đẩy code lên, chạy lệnh trong thư mục của dự án để git có thể nhận diện được dự án đó:**

  - Thêm dữ liệu mới / code đã sửa:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git add .
  ```

  - Thêm ghi chú cho phần code dữ liệu mới mà mình muốn đẩy lên:
    
  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git commit -m "Đây là ghi chú của tôi cho phần code mới mà tôi đã sửa bởi Draken"
  ```

  - Bắt đầu đẩy dữ liệu cùng ghi chú lên:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git push
  ```

  Hoặc đầy đủ hơn:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git push origin main
  ```

  Trong đó:

  - **origin** là kho dữ liệu mà mình đang điều khiển từ xa (từ máy chủ của GitHub)
  - **main** là branch mà mình muốn đẩy dữ liệu lên

  3.4. Chuyển sang branch khác để làm việc

  Ví dụ Project này có 2 branch là **main** và **test**, **main** là mặc định được chọn bởi Git khi clone về thì để di chuyển sang branch **test**, ta nhập lệnh sau:

  - Tải lại toàn bộ dữ liệu từ GitHub về máy:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git pull
  ```

  - Chuyển sang branch test:

  ```cmd
  C:\Users\Dra\Desktop\iStars-store-software> git checkout test
  ```

  **Có thể chuyển qua chuyển lại giữa các branch mà bạn đã tạo trên GitHub, nó sẽ được đồng bộ với máy tính của bạn khi dùng lệnh** `git pull` 👌

THE END.