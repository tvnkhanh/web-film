USE [master]
GO
/****** Object:  Database [WEBPHIM]    Script Date: 4/30/2023 11:11:56 PM ******/
CREATE DATABASE [WEBPHIM]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'WEBPHIM', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\WEBPHIM.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'WEBPHIM_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\WEBPHIM_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [WEBPHIM] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [WEBPHIM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [WEBPHIM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [WEBPHIM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [WEBPHIM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [WEBPHIM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [WEBPHIM] SET ARITHABORT OFF 
GO
ALTER DATABASE [WEBPHIM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [WEBPHIM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [WEBPHIM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [WEBPHIM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [WEBPHIM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [WEBPHIM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [WEBPHIM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [WEBPHIM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [WEBPHIM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [WEBPHIM] SET  ENABLE_BROKER 
GO
ALTER DATABASE [WEBPHIM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [WEBPHIM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [WEBPHIM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [WEBPHIM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [WEBPHIM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [WEBPHIM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [WEBPHIM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [WEBPHIM] SET RECOVERY FULL 
GO
ALTER DATABASE [WEBPHIM] SET  MULTI_USER 
GO
ALTER DATABASE [WEBPHIM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [WEBPHIM] SET DB_CHAINING OFF 
GO
ALTER DATABASE [WEBPHIM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [WEBPHIM] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [WEBPHIM] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [WEBPHIM] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'WEBPHIM', N'ON'
GO
ALTER DATABASE [WEBPHIM] SET QUERY_STORE = OFF
GO
USE [WEBPHIM]
GO
/****** Object:  Table [dbo].[CHUCDANH]    Script Date: 4/30/2023 11:11:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUCDANH](
	[ID_CD] [int] IDENTITY(1,1) NOT NULL,
	[TEN_CD] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_CHUCDANH] PRIMARY KEY CLUSTERED 
(
	[ID_CD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_MUA]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_MUA](
	[ID_MUA] [int] IDENTITY(1,1) NOT NULL,
	[ID_GOI] [int] NOT NULL,
	[TEN_TK] [nvarchar](50) NOT NULL,
	[NGAYMUA] [date] NULL,
	[SONGAY] [int] NOT NULL,
	[GIA] [money] NULL,
 CONSTRAINT [PK_CT_MUA] PRIMARY KEY CLUSTERED 
(
	[ID_MUA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_THELOAI]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_THELOAI](
	[ID_TL] [int] NOT NULL,
	[ID_PHIM] [int] NOT NULL,
 CONSTRAINT [PK_CT_THELOAI] PRIMARY KEY CLUSTERED 
(
	[ID_TL] ASC,
	[ID_PHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_THEODOI]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_THEODOI](
	[ID_DSTD] [int] NOT NULL,
	[ID_PHIM] [int] NOT NULL,
 CONSTRAINT [PK_CT_THEODOI] PRIMARY KEY CLUSTERED 
(
	[ID_DSTD] ASC,
	[ID_PHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_YEUTHICH]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_YEUTHICH](
	[ID_DSYT] [int] NOT NULL,
	[ID_PHIM] [int] NOT NULL,
 CONSTRAINT [PK_CT_YEUTHICH] PRIMARY KEY CLUSTERED 
(
	[ID_DSYT] ASC,
	[ID_PHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DSTHEODOI]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DSTHEODOI](
	[ID_DSTD] [int] IDENTITY(1,1) NOT NULL,
	[ID_ND] [int] NOT NULL,
 CONSTRAINT [PK_DSTHEODOI] PRIMARY KEY CLUSTERED 
(
	[ID_DSTD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DSYEUTHICH]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DSYEUTHICH](
	[ID_DSYT] [int] IDENTITY(1,1) NOT NULL,
	[ID_ND] [int] NOT NULL,
 CONSTRAINT [PK_DSYEUTHICH] PRIMARY KEY CLUSTERED 
(
	[ID_DSYT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GOIHOIVIEN]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GOIHOIVIEN](
	[ID_GOI] [int] IDENTITY(1,1) NOT NULL,
	[SONGAY] [int] NOT NULL,
	[GIA] [money] NOT NULL,
 CONSTRAINT [PK_GOIHOIVIEN] PRIMARY KEY CLUSTERED 
(
	[ID_GOI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NGUOIDUNG]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NGUOIDUNG](
	[ID_ND] [int] IDENTITY(1,1) NOT NULL,
	[HOTEN] [nvarchar](60) NOT NULL,
	[ANH] [varchar](200) NULL,
	[EMAIL] [varchar](50) NOT NULL,
	[GIOITINH] [nvarchar](10) NULL,
	[TEN_TK] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NGUOIDUNG] PRIMARY KEY CLUSTERED 
(
	[ID_ND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIM]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIM](
	[ID_PHIM] [int] IDENTITY(1,1) NOT NULL,
	[TENPHIM] [nvarchar](100) NOT NULL,
	[ANH] [nvarchar](100) NULL,
	[MOTA] [nvarchar](500) NULL,
	[LOAI] [varchar](10) NULL,
 CONSTRAINT [PK_PHIM] PRIMARY KEY CLUSTERED 
(
	[ID_PHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[TEN_TK] [nvarchar](50) NOT NULL,
	[MATKHAU] [nvarchar](255) NOT NULL,
	[NGAYTAO] [date] NULL,
	[TRANGTHAI] [bit] NULL,
	[ID_CD] [int] NOT NULL,
 CONSTRAINT [PK_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[TEN_TK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAP]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAP](
	[ID_TAP] [int] IDENTITY(1,1) NOT NULL,
	[TEN_TAP] [int] NOT NULL,
	[LUOTXEM] [bigint] NULL,
	[NGAYDANG] [date] NULL,
	[ANH] [varchar](200) NULL,
	[LINKVD] [varchar](1000) NULL,
	[ID_PHIM] [int] NOT NULL,
 CONSTRAINT [PK_TAP_1] PRIMARY KEY CLUSTERED 
(
	[ID_TAP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THELOAI]    Script Date: 4/30/2023 11:11:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THELOAI](
	[ID_TL] [int] IDENTITY(1,1) NOT NULL,
	[TEN_TL] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_THELOAI] PRIMARY KEY CLUSTERED 
(
	[ID_TL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CHUCDANH] ON 

INSERT [dbo].[CHUCDANH] ([ID_CD], [TEN_CD]) VALUES (1, N'Member')
INSERT [dbo].[CHUCDANH] ([ID_CD], [TEN_CD]) VALUES (3, N'Owner')
INSERT [dbo].[CHUCDANH] ([ID_CD], [TEN_CD]) VALUES (2, N'VIP')
SET IDENTITY_INSERT [dbo].[CHUCDANH] OFF
GO
SET IDENTITY_INSERT [dbo].[CT_MUA] ON 

INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (1, 1, N'membervip', CAST(N'2023-04-13' AS Date), 30, 45000.0000)
INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (2, 3, N'hodat123', CAST(N'2023-04-13' AS Date), 60, 80000.0000)
INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (3, 3, N'member', CAST(N'2023-04-13' AS Date), 60, 80000.0000)
INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (4, 4, N'admin', CAST(N'2023-04-13' AS Date), 90, 120000.0000)
INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (5, 4, N'member', CAST(N'2023-04-17' AS Date), 90, 120000.0000)
INSERT [dbo].[CT_MUA] ([ID_MUA], [ID_GOI], [TEN_TK], [NGAYMUA], [SONGAY], [GIA]) VALUES (6, 5, N'member', CAST(N'2023-04-18' AS Date), 160, 185000.0000)
SET IDENTITY_INSERT [dbo].[CT_MUA] OFF
GO
INSERT [dbo].[CT_THELOAI] ([ID_TL], [ID_PHIM]) VALUES (3, 2)
INSERT [dbo].[CT_THELOAI] ([ID_TL], [ID_PHIM]) VALUES (4, 1)
INSERT [dbo].[CT_THELOAI] ([ID_TL], [ID_PHIM]) VALUES (6, 2)
INSERT [dbo].[CT_THELOAI] ([ID_TL], [ID_PHIM]) VALUES (7, 1)
GO
INSERT [dbo].[CT_THEODOI] ([ID_DSTD], [ID_PHIM]) VALUES (1, 1)
GO
INSERT [dbo].[CT_YEUTHICH] ([ID_DSYT], [ID_PHIM]) VALUES (2, 1)
GO
SET IDENTITY_INSERT [dbo].[DSTHEODOI] ON 

INSERT [dbo].[DSTHEODOI] ([ID_DSTD], [ID_ND]) VALUES (1, 1)
INSERT [dbo].[DSTHEODOI] ([ID_DSTD], [ID_ND]) VALUES (2, 2)
INSERT [dbo].[DSTHEODOI] ([ID_DSTD], [ID_ND]) VALUES (3, 10)
SET IDENTITY_INSERT [dbo].[DSTHEODOI] OFF
GO
SET IDENTITY_INSERT [dbo].[DSYEUTHICH] ON 

INSERT [dbo].[DSYEUTHICH] ([ID_DSYT], [ID_ND]) VALUES (2, 1)
INSERT [dbo].[DSYEUTHICH] ([ID_DSYT], [ID_ND]) VALUES (3, 2)
INSERT [dbo].[DSYEUTHICH] ([ID_DSYT], [ID_ND]) VALUES (4, 10)
SET IDENTITY_INSERT [dbo].[DSYEUTHICH] OFF
GO
SET IDENTITY_INSERT [dbo].[GOIHOIVIEN] ON 

INSERT [dbo].[GOIHOIVIEN] ([ID_GOI], [SONGAY], [GIA]) VALUES (1, 30, 45000.0000)
INSERT [dbo].[GOIHOIVIEN] ([ID_GOI], [SONGAY], [GIA]) VALUES (3, 60, 80000.0000)
INSERT [dbo].[GOIHOIVIEN] ([ID_GOI], [SONGAY], [GIA]) VALUES (4, 90, 120000.0000)
INSERT [dbo].[GOIHOIVIEN] ([ID_GOI], [SONGAY], [GIA]) VALUES (5, 120, 160000.0000)
INSERT [dbo].[GOIHOIVIEN] ([ID_GOI], [SONGAY], [GIA]) VALUES (6, 160, 185000.0000)
SET IDENTITY_INSERT [dbo].[GOIHOIVIEN] OFF
GO
SET IDENTITY_INSERT [dbo].[NGUOIDUNG] ON 

INSERT [dbo].[NGUOIDUNG] ([ID_ND], [HOTEN], [ANH], [EMAIL], [GIOITINH], [TEN_TK]) VALUES (1, N'Hammer', N'https://3.pik.vn/2020cb482aa9-79bc-402c-a5ed-dcaa321f7ddd.png', N'hodat2215@gmail.com', N'Nam', N'admin')
INSERT [dbo].[NGUOIDUNG] ([ID_ND], [HOTEN], [ANH], [EMAIL], [GIOITINH], [TEN_TK]) VALUES (2, N'Thị Nở', N'https://i0.wp.com/thatnhucuocsong.com.vn/wp-content/uploads/2022/03/anh-meo-bua-buon-cuoi-lay-loi-cuc-ky.jpg?ssl=1', N'baam036@gmail.com', N'Nữ', N'membervip')
INSERT [dbo].[NGUOIDUNG] ([ID_ND], [HOTEN], [ANH], [EMAIL], [GIOITINH], [TEN_TK]) VALUES (3, N'Mắm tôm', N'https://tackexinh.com/wp-content/uploads/2022/03/anh-meo-che-anh-meo-bua-22.jpg', N'acb@gmail.com', N'Nam', N'member')
INSERT [dbo].[NGUOIDUNG] ([ID_ND], [HOTEN], [ANH], [EMAIL], [GIOITINH], [TEN_TK]) VALUES (10, N'Ro me O', N'https://i.pinimg.com/474x/a1/8d/56/a18d56104193738a5ac7b9bfb43ba09d.jpg', N'vaicmnr1234567@gmail.com', N'Nam', N'hodat123')
SET IDENTITY_INSERT [dbo].[NGUOIDUNG] OFF
GO
SET IDENTITY_INSERT [dbo].[PHIM] ON 

INSERT [dbo].[PHIM] ([ID_PHIM], [TENPHIM], [ANH], [MOTA], [LOAI]) VALUES (1, N'Boku no Hero Academia', N'https://news-w.com/wp-content/uploads/2022/07/my-hero-academia-thumbnail.jpg', N'Hay', N'Free')
INSERT [dbo].[PHIM] ([ID_PHIM], [TENPHIM], [ANH], [MOTA], [LOAI]) VALUES (2, N'Re:Zero kara Hajimeru Isekai Seikatsu', N'https://cdn.akamai.steamstatic.com/steam/apps/1277510/capsule_616x353.jpg?t=1611984622', N'...', N'Free')
SET IDENTITY_INSERT [dbo].[PHIM] OFF
GO
INSERT [dbo].[TAIKHOAN] ([TEN_TK], [MATKHAU], [NGAYTAO], [TRANGTHAI], [ID_CD]) VALUES (N'admin', N'123456', CAST(N'2023-03-19' AS Date), 1, 3)
INSERT [dbo].[TAIKHOAN] ([TEN_TK], [MATKHAU], [NGAYTAO], [TRANGTHAI], [ID_CD]) VALUES (N'hodat123', N'123456', CAST(N'2023-03-19' AS Date), 1, 1)
INSERT [dbo].[TAIKHOAN] ([TEN_TK], [MATKHAU], [NGAYTAO], [TRANGTHAI], [ID_CD]) VALUES (N'member', N'123456', CAST(N'2023-03-19' AS Date), 1, 1)
INSERT [dbo].[TAIKHOAN] ([TEN_TK], [MATKHAU], [NGAYTAO], [TRANGTHAI], [ID_CD]) VALUES (N'membervip', N'123456', CAST(N'2023-03-19' AS Date), 1, 2)
GO
SET IDENTITY_INSERT [dbo].[TAP] ON 

INSERT [dbo].[TAP] ([ID_TAP], [TEN_TAP], [LUOTXEM], [NGAYDANG], [ANH], [LINKVD], [ID_PHIM]) VALUES (1, 1, 3, CAST(N'2023-04-10' AS Date), N'', N'https://www.youtube.com/watch?v=kDBgSpklgyc&list=RDGMEMPdLDZ-FVVWuzckFEguTm5Q&start_radio=1&rv=GH_llmHNxeA', 1)
INSERT [dbo].[TAP] ([ID_TAP], [TEN_TAP], [LUOTXEM], [NGAYDANG], [ANH], [LINKVD], [ID_PHIM]) VALUES (2, 2, 4, CAST(N'2023-04-10' AS Date), N'', N'https://www.youtube.com/watch?v=kDBgSpklgyc&list=RDGMEMPdLDZ-FVVWuzckFEguTm5Q&start_radio=1&rv=GH_llmHNxeA', 1)
INSERT [dbo].[TAP] ([ID_TAP], [TEN_TAP], [LUOTXEM], [NGAYDANG], [ANH], [LINKVD], [ID_PHIM]) VALUES (3, 1, 9, CAST(N'2023-04-10' AS Date), N'', N'https://www.youtube.com/watch?v=Slz_rahWp6Y', 2)
SET IDENTITY_INSERT [dbo].[TAP] OFF
GO
SET IDENTITY_INSERT [dbo].[THELOAI] ON 

INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (7, N'Comedy')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (6, N'Drama')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (2, N'Ecchi')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (3, N'Fantasy')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (5, N'Romance')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (1, N'Seinen')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (4, N'Shounen')
INSERT [dbo].[THELOAI] ([ID_TL], [TEN_TL]) VALUES (8, N'Slice of Life')
SET IDENTITY_INSERT [dbo].[THELOAI] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_TENCD]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[CHUCDANH] ADD  CONSTRAINT [UK_TENCD] UNIQUE NONCLUSTERED 
(
	[TEN_CD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UK_NDTD]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[DSTHEODOI] ADD  CONSTRAINT [UK_NDTD] UNIQUE NONCLUSTERED 
(
	[ID_ND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UK_NGYT]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[DSYEUTHICH] ADD  CONSTRAINT [UK_NGYT] UNIQUE NONCLUSTERED 
(
	[ID_ND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_TK]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[NGUOIDUNG] ADD  CONSTRAINT [UK_TK] UNIQUE NONCLUSTERED 
(
	[TEN_TK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_TENPHIM]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[PHIM] ADD  CONSTRAINT [UK_TENPHIM] UNIQUE NONCLUSTERED 
(
	[TENPHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_TENTT]    Script Date: 4/30/2023 11:11:57 PM ******/
ALTER TABLE [dbo].[THELOAI] ADD  CONSTRAINT [UK_TENTT] UNIQUE NONCLUSTERED 
(
	[TEN_TL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_IDTL]    Script Date: 4/30/2023 11:11:57 PM ******/
CREATE NONCLUSTERED INDEX [IX_IDTL] ON [dbo].[THELOAI]
(
	[ID_TL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CT_MUA] ADD  CONSTRAINT [DF_CT_MUA_NGAYMUA]  DEFAULT (getdate()) FOR [NGAYMUA]
GO
ALTER TABLE [dbo].[NGUOIDUNG] ADD  CONSTRAINT [DF_NGUOIDUNG_EMAIL]  DEFAULT ('') FOR [EMAIL]
GO
ALTER TABLE [dbo].[NGUOIDUNG] ADD  CONSTRAINT [DF_NGUOIDUNG_GIOITINH]  DEFAULT (N'Không nói') FOR [GIOITINH]
GO
ALTER TABLE [dbo].[PHIM] ADD  CONSTRAINT [DF_PHIM_ANH]  DEFAULT ('') FOR [ANH]
GO
ALTER TABLE [dbo].[PHIM] ADD  CONSTRAINT [DF_PHIM_MOTA]  DEFAULT ('') FOR [MOTA]
GO
ALTER TABLE [dbo].[PHIM] ADD  CONSTRAINT [DF_PHIM_LOAI]  DEFAULT (N'Free') FOR [LOAI]
GO
ALTER TABLE [dbo].[TAIKHOAN] ADD  CONSTRAINT [DF_TAIKHOAN_NGAYTAO]  DEFAULT (getdate()) FOR [NGAYTAO]
GO
ALTER TABLE [dbo].[TAIKHOAN] ADD  CONSTRAINT [DF_TAIKHOAN_TRANGTHAI]  DEFAULT ((1)) FOR [TRANGTHAI]
GO
ALTER TABLE [dbo].[TAP] ADD  CONSTRAINT [DF_TAP_LUOTXEM_1]  DEFAULT ((0)) FOR [LUOTXEM]
GO
ALTER TABLE [dbo].[TAP] ADD  CONSTRAINT [DF_TAP_NGAYDANG_1]  DEFAULT (getdate()) FOR [NGAYDANG]
GO
ALTER TABLE [dbo].[TAP] ADD  CONSTRAINT [DF_TAP_ANH_1]  DEFAULT ('') FOR [ANH]
GO
ALTER TABLE [dbo].[CT_MUA]  WITH CHECK ADD  CONSTRAINT [FK_CT_MUA_GOIHOIVIEN] FOREIGN KEY([ID_GOI])
REFERENCES [dbo].[GOIHOIVIEN] ([ID_GOI])
GO
ALTER TABLE [dbo].[CT_MUA] CHECK CONSTRAINT [FK_CT_MUA_GOIHOIVIEN]
GO
ALTER TABLE [dbo].[CT_MUA]  WITH CHECK ADD  CONSTRAINT [FK_CT_MUA_TAIKHOAN] FOREIGN KEY([TEN_TK])
REFERENCES [dbo].[TAIKHOAN] ([TEN_TK])
GO
ALTER TABLE [dbo].[CT_MUA] CHECK CONSTRAINT [FK_CT_MUA_TAIKHOAN]
GO
ALTER TABLE [dbo].[CT_THELOAI]  WITH CHECK ADD  CONSTRAINT [FK_CT_THELOAI_PHIM] FOREIGN KEY([ID_PHIM])
REFERENCES [dbo].[PHIM] ([ID_PHIM])
GO
ALTER TABLE [dbo].[CT_THELOAI] CHECK CONSTRAINT [FK_CT_THELOAI_PHIM]
GO
ALTER TABLE [dbo].[CT_THELOAI]  WITH CHECK ADD  CONSTRAINT [FK_CT_THELOAI_THELOAI] FOREIGN KEY([ID_TL])
REFERENCES [dbo].[THELOAI] ([ID_TL])
GO
ALTER TABLE [dbo].[CT_THELOAI] CHECK CONSTRAINT [FK_CT_THELOAI_THELOAI]
GO
ALTER TABLE [dbo].[CT_THEODOI]  WITH CHECK ADD  CONSTRAINT [FK_CT_THEODOI_DSTHEODOI1] FOREIGN KEY([ID_DSTD])
REFERENCES [dbo].[DSTHEODOI] ([ID_DSTD])
GO
ALTER TABLE [dbo].[CT_THEODOI] CHECK CONSTRAINT [FK_CT_THEODOI_DSTHEODOI1]
GO
ALTER TABLE [dbo].[CT_THEODOI]  WITH CHECK ADD  CONSTRAINT [FK_CT_THEODOI_PHIM] FOREIGN KEY([ID_PHIM])
REFERENCES [dbo].[PHIM] ([ID_PHIM])
GO
ALTER TABLE [dbo].[CT_THEODOI] CHECK CONSTRAINT [FK_CT_THEODOI_PHIM]
GO
ALTER TABLE [dbo].[CT_YEUTHICH]  WITH CHECK ADD  CONSTRAINT [FK_CT_YEUTHICH_DSYEUTHICH] FOREIGN KEY([ID_DSYT])
REFERENCES [dbo].[DSYEUTHICH] ([ID_DSYT])
GO
ALTER TABLE [dbo].[CT_YEUTHICH] CHECK CONSTRAINT [FK_CT_YEUTHICH_DSYEUTHICH]
GO
ALTER TABLE [dbo].[CT_YEUTHICH]  WITH CHECK ADD  CONSTRAINT [FK_CT_YEUTHICH_PHIM1] FOREIGN KEY([ID_PHIM])
REFERENCES [dbo].[PHIM] ([ID_PHIM])
GO
ALTER TABLE [dbo].[CT_YEUTHICH] CHECK CONSTRAINT [FK_CT_YEUTHICH_PHIM1]
GO
ALTER TABLE [dbo].[DSTHEODOI]  WITH CHECK ADD  CONSTRAINT [FK_DSTHEODOI_NGUOIDUNG] FOREIGN KEY([ID_ND])
REFERENCES [dbo].[NGUOIDUNG] ([ID_ND])
GO
ALTER TABLE [dbo].[DSTHEODOI] CHECK CONSTRAINT [FK_DSTHEODOI_NGUOIDUNG]
GO
ALTER TABLE [dbo].[DSYEUTHICH]  WITH CHECK ADD  CONSTRAINT [FK_DSYEUTHICH_NGUOIDUNG] FOREIGN KEY([ID_ND])
REFERENCES [dbo].[NGUOIDUNG] ([ID_ND])
GO
ALTER TABLE [dbo].[DSYEUTHICH] CHECK CONSTRAINT [FK_DSYEUTHICH_NGUOIDUNG]
GO
ALTER TABLE [dbo].[NGUOIDUNG]  WITH CHECK ADD  CONSTRAINT [FK_NGUOIDUNG_TAIKHOAN] FOREIGN KEY([TEN_TK])
REFERENCES [dbo].[TAIKHOAN] ([TEN_TK])
GO
ALTER TABLE [dbo].[NGUOIDUNG] CHECK CONSTRAINT [FK_NGUOIDUNG_TAIKHOAN]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [FK_TAIKHOAN_CHUCDANH] FOREIGN KEY([ID_CD])
REFERENCES [dbo].[CHUCDANH] ([ID_CD])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [FK_TAIKHOAN_CHUCDANH]
GO
ALTER TABLE [dbo].[CT_MUA]  WITH CHECK ADD  CONSTRAINT [CK_MUA_SN] CHECK  (([SONGAY]>(0)))
GO
ALTER TABLE [dbo].[CT_MUA] CHECK CONSTRAINT [CK_MUA_SN]
GO
ALTER TABLE [dbo].[GOIHOIVIEN]  WITH CHECK ADD  CONSTRAINT [CK_GIA] CHECK  (([GIA]>(0)))
GO
ALTER TABLE [dbo].[GOIHOIVIEN] CHECK CONSTRAINT [CK_GIA]
GO
ALTER TABLE [dbo].[GOIHOIVIEN]  WITH CHECK ADD  CONSTRAINT [CK_SONGAY] CHECK  (([SONGAY]>(0)))
GO
ALTER TABLE [dbo].[GOIHOIVIEN] CHECK CONSTRAINT [CK_SONGAY]
GO
ALTER TABLE [dbo].[NGUOIDUNG]  WITH CHECK ADD  CONSTRAINT [CK_GIOITINH] CHECK  (([GIOITINH]=N'NAM' OR [GIOITINH]=N'NỮ' OR [GIOITINH]=N'KHÔNG NÓI'))
GO
ALTER TABLE [dbo].[NGUOIDUNG] CHECK CONSTRAINT [CK_GIOITINH]
GO
ALTER TABLE [dbo].[PHIM]  WITH CHECK ADD  CONSTRAINT [CK_PHIM] CHECK  (([LOAI]=N'FREE' OR [LOAI]=N'LIMIT'))
GO
ALTER TABLE [dbo].[PHIM] CHECK CONSTRAINT [CK_PHIM]
GO
USE [master]
GO
ALTER DATABASE [WEBPHIM] SET  READ_WRITE 
GO
