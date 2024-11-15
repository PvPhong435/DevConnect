CREATE DATABASE DevConnect

GO

USE DevConnect

GO
-- chứa thông tin người dùng cũng như quản trị viên
CREATE TABLE Users
(
	username VARCHAR(50),
	password varchar(50),
	role nvarchar(50),
	fullname NVARCHAR(50),
	email varchar(50),
	phone varchar(13),
	address nvarchar(100),
	primary key(username)
);

go
--chứa loại bài viết
CREATE TABLE Categories
(
	categoryId VARCHAR(50),
	categoryName NVARCHAR(50),
	primary key(categoryId)
);

GO
--chứa thông tin bài viết
CREATE TABLE Articles
(	
	articleId VARCHAR(50),
	title NVARCHAR(100),
	content NVARCHAR(max),
	categoryid VARCHAR(50),
	author varchar(50),
	createAt datetime,
	updateAt datetime,
	primary key(articleId),
	FOREIGN KEY(categoryid) REFERENCES Categories(categoryId),
	FOREIGN KEY(author) REFERENCES Users(username),
);

GO
--đếm lượt truy cập vào bài viết
CREATE TABLE CountView
(
	articleId VARCHAR(50),
	viewCount int,
	PRIMARY KEY(articleId),
	FOREIGN KEY(articleId) REFERENCES Articles(articleId),
);

GO
--chứa thông tin các dòng bình luận lên 1 bài viết
CREATE TABLE Comments
(
	commentid VARCHAR(50),
	articleId VARCHAR(50),
	username varchar(50),
	content NVARCHAR(MAX),
	createAt datetime,
	PRIMARY KEY(commentid),
	FOREIGN KEY(articleId) REFERENCES Articles(articleId),
	FOREIGN KEY(username) REFERENCES Users(username),
);

GO
--chứa thông tin các thẻ tag 
CREATE TABLE Tags
(
	tagId varchar(50),
	nameTag nvarchar(50),
	PRIMARY KEY(tagId)
);

GO

--Chứa thông tin thẻ tag của bài viết
CREATE TABLE ArticleTags
(
	articleId varchar(50),
	tagId varchar(50),
	PRIMARY KEY(articleId,tagId),
	FOREIGN KEY(articleId) REFERENCES Articles(articleId),
	FOREIGN KEY(tagId) REFERENCES Tags(tagId),
);

GO
--chứa thông tin số lược like của từng comment
CREATE TABLE CommentLike
(
	commentid VARCHAR(50),
	username varchar(50),
	numLike int,
	PRIMARY KEY(commentid,username),
	FOREIGN KEY(commentid) REFERENCES Comments(commentid),
	FOREIGN KEY(username) REFERENCES Users(username),
);

GO

CREATE TABLE DoccumentArticles
(
	articleId varchar(50),
	username varchar(50),
	linkDrive varchar(50),
	PRIMARY KEY(articleId,username),
	FOREIGN KEY(articleId) REFERENCES Articles(articleId),
	FOREIGN KEY(username) REFERENCES Users(username),
);

GO

INSERT INTO Users (username, password, role, fullname, email, phone, address) VALUES
('haotg', 'haotg123', 'admin', N'Trương Gia Hào', 'user1@example.com', '0901000001', N'123 Đường A, TP.HCM'),
('phongpv', 'phongpv123', 'admin', N'Phạm Văn Phong', 'user2@example.com', '0901000002', N'456 Đường B, TP.HCM'),
('chautnb', 'chautnb123', 'admin', N'Trần Ngọc Bội Châu', 'user3@example.com', '0901000003', N'789 Đường C, Hà Nội'),
('thangnc', 'thangnc123', 'admin', N'Nguyễn Cao Thăng', 'user4@example.com', '0901000004', N'321 Đường D, Đà Nẵng'),
('tinttt', 'tinttt123', 'admin', N'Trần Thái Trọng Tín', 'user5@example.com', '0901000005', N'654 Đường E, Cần Thơ'),
('thanhtd', 'thanhtd123', 'admin', N'Trương Đại Thành', 'user6@example.com', '0901000006', N'987 Đường F, TP.HCM'),
('user7', 'password7', 'user', N'Đặng Văn G', 'user7@example.com', '0901000007', N'123 Đường G, Hà Nội'),
('user8', 'password8', 'user', N'Ngô Thị H', 'user8@example.com', '0901000008', N'456 Đường H, Đà Nẵng'),
('user9', 'password9', 'user', N'Phan Văn I', 'user9@example.com', '0901000009', N'789 Đường I, Cần Thơ'),
('user10', 'password10', 'user', N'Vũ Thị J', 'user10@example.com', '0901000010', N'321 Đường J, TP.HCM');

GO

INSERT INTO Categories (categoryId, categoryName) VALUES
('CNPM1', N'Công nghệ phần mềm'),
('LTW1', N'Lập trình web'),
('PTDL1', N'Phân tích dữ liệu'),
('JV1', N'Lập Trình Java'),
('KHDL5', N'Khoa học dữ liệu'),
('TTNT6', N'Trí tuệ nhân tạo'),
('ANM7', N'An ninh mạng'),
('PTDD8', N'Phát triển di động'),
('BC9', N'Blockchain'),
('TKDH10', N'Thiết kế đồ họa');

GO

INSERT INTO Articles (articleId, title, content, categoryid, author, createAt, updateAt) VALUES
('art1', N'Bài viết về công nghệ phần mềm', N'Nội dung bài viết 1...', 'CNPM1', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art2', N'Lập trình web từ cơ bản đến nâng cao', N'Nội dung bài viết 2...', 'LTW1', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art3', N'Phân tích dữ liệu cho người mới', N'Nội dung bài viết 3...', 'PTDL1', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art4', N'Lập Trình Java nâng cao', N'Nội dung bài viết 4...', 'JV1', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art5', N'Tìm hiểu về khoa học dữ liệu', N'Nội dung bài viết 5...', 'KHDL5', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art6', N'Khái niệm về trí tuệ nhân tạo', N'Nội dung bài viết 6...', 'TTNT6', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art7', N'An ninh mạng trong thời đại số', N'Nội dung bài viết 7...', 'ANM7', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art8', N'Phát triển di động với React Native', N'Nội dung bài viết 8...', 'PTDD8', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art9', N'Blockchain và tương lai của tiền điện tử', N'Nội dung bài viết 9...', 'BC9', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00'),
('art10', N'Thiết kế đồ họa cơ bản với Photoshop', N'Nội dung bài viết 10...', 'TKDH10', 'thangnc', '2024-11-07 12:00:00', '2024-11-07 12:00:00');

GO

INSERT INTO CountView (articleId, viewCount) VALUES
('art1', 100),
('art2', 150),
('art3', 200),
('art4', 120),
('art5', 250),
('art6', 300),
('art7', 180),
('art8', 220),
('art9', 90),
('art10', 160);

GO

INSERT INTO Comments (commentid, articleId, username, content, createAt) VALUES
('com1', 'art1', 'phongpv', N'Bài viết rất hữu ích, cảm ơn tác giả!', '2024-11-07 12:10:00'),
('com2', 'art2', 'phongpv', N'Bài viết rất chi tiết và dễ hiểu.', '2024-11-07 12:20:00'),
('com3', 'art3', 'phongpv', N'Thông tin phân tích dữ liệu rất bổ ích!', '2024-11-07 12:30:00'),
('com4', 'art4', 'phongpv', N'Giới thiệu về Java rất thú vị, cảm ơn bạn!', '2024-11-07 12:40:00'),
('com5', 'art5', 'phongpv', N'Khoa học dữ liệu ngày càng trở nên quan trọng.', '2024-11-07 12:50:00'),
('com6', 'art6', 'phongpv', N'Trí tuệ nhân tạo là tương lai của ngành công nghiệp.', '2024-11-07 13:00:00'),
('com7', 'art7', 'phongpv', N'An ninh mạng rất quan trọng trong thời đại số.', '2024-11-07 13:10:00'),
('com8', 'art8', 'phongpv', N'React Native là công cụ phát triển di động tuyệt vời.', '2024-11-07 13:20:00'),
('com9', 'art9', 'phongpv', N'Blockchain sẽ thay đổi hoàn toàn thế giới tài chính.', '2024-11-07 13:30:00'),
('com10', 'art10', 'phongpv', N'Thiết kế đồ họa là một nghệ thuật đỉnh cao.', '2024-11-07 13:40:00');

GO

INSERT INTO Tags (tagId, nameTag) VALUES
('#java', N'Java'),
('#python', N'Python'),
('#machinelearning', N'Machine Learning'),
('#blockchain', N'Blockchain'),
('#trituenhantao', N'Trí tuệ nhân tạo'),
('#anninhmang', N'An ninh mạng'),
('#khoahocdulieu', N'Khoa học dữ liệu'),
('#reactnative', N'React Native'),
('#webdevelopment', N'Web Development'),
('#thietkedohoa', N'Thiết kế đồ họa');

GO

INSERT INTO ArticleTags (articleId, tagId) VALUES
('art1', '#java'),
('art1', '#machinelearning'),
('art2', '#python'),
('art2', '#webdevelopment'),
('art3', '#machinelearning'),
('art3', '#khoahocdulieu'),
('art4', '#java'),
('art4', '#webdevelopment'),
('art5', '#trituenhantao'),
('art5', '#khoahocdulieu'),
('art6', '#trituenhantao'),
('art6', '#thietkedohoa'),
('art7', '#anninhmang'),
('art7', '#java'),
('art8', '#reactnative'),
('art8', '#python'),
('art9', '#blockchain'),
('art9', '#anninhmang'),
('art10', '#thietkedohoa'),
('art10', '#webdevelopment');

go

INSERT INTO CommentLike (commentid, username, numLike) VALUES
('com1', 'phongpv', 15),
('com2', 'phongpv', 20),
('com3', 'phongpv', 10),
('com4', 'phongpv', 5),
('com5', 'phongpv', 30),
('com6', 'phongpv', 8),
('com7', 'phongpv', 18),
('com8', 'phongpv', 12),
('com9', 'phongpv', 25),
('com10', 'phongpv', 40);

GO

INSERT INTO DoccumentArticles (articleId, username, linkDrive) VALUES
('art1', 'phongpv', 'https://drive.google.com/file1'),
('art1', 'thangnc', 'https://drive.google.com/file2'),
('art2', 'phongpv', 'https://drive.google.com/file3'),
('art2', 'thangnc', 'https://drive.google.com/file4'),
('art3', 'phongpv', 'https://drive.google.com/file5'),
('art4', 'thangnc', 'https://drive.google.com/file6'),
('art5', 'phongpv', 'https://drive.google.com/file7'),
('art6', 'thangnc', 'https://drive.google.com/file8'),
('art7', 'phongpv', 'https://drive.google.com/file9'),
('art8', 'thangnc', 'https://drive.google.com/file10');






