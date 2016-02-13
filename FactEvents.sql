DROP TABLE [6NDims].[dbo].[FactEvents]
GO
CREATE TABLE [6NDims].[dbo].[FactEvents](
	[FactID] [bigint] IDENTITY(1,1) NOT NULL,
	[opta_event_id] [bigint] NOT NULL,
	[fixture_id] [bigint] NOT NULL,
	[away_score] [smallint] NULL,
	[home_score] [smallint] NULL,
	[period] [varchar](30) NULL,
	[timestamp] [datetime] NULL,
	[timestamp_milliseconds] [varchar](3) NULL,
	[period_id] [smallint] NULL,
	[period_minute] [tinyint] NULL,
	[period_second] [tinyint] NULL,
	[player_id] [bigint] NULL,
	[event_id] [smallint] NULL,
	[event_type_id] [smallint] NULL,
	[event_type_name] [varchar](30) NULL,
	[qualifier_1] [varchar](50) NULL,
	[qualifier_2] [varchar](50) NULL,
	[qualifier_3] [varchar](50) NULL,
	[qualifier_4] [varchar](50) NULL,
	[qualifier_5] [varchar](50) NULL,
	[team_id] [bigint] NOT NULL,
	[outcome] [smallint] NULL,
	[metres] [decimal](5, 2) NULL,
	[score_difference] [int] NULL,
	[game_status] [varchar](30) NULL,
	[timer_running] [tinyint] NULL,
	[direction] [varchar](13) NULL,
	[direction_Of_play] [varchar](30) NULL,
	[direction_of_play_x] [decimal](5, 2) NULL,
	[direction_of_play_end_x] [decimal](5, 2) NULL,
	[direction_of_play_y] [decimal](5, 2) NULL,
	[direction_of_play_end_y] [decimal](5, 2) NULL,
	[x] [decimal](5, 2) NULL,
	[y] [decimal](5, 2) NULL,
	[possession] [smallint] NULL,
	[last_modified] [datetime] NULL,
	[related_event_id] [int] NULL,
	[position] [varchar](64) NULL,
	[jersey_number] [smallint] NULL,
	[deleted_flag] [tinyint] NULL,
	[audit_id] [bigint] NULL,
	[last_update_date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[opta_event_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO