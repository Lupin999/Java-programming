import requests # pip install requests
import json
import sqlite3
import pandas as pd
from datetime import datetime
import time

# Load configuration from config.json
with open("config.json", "r") as config_file:
    config = json.load(config_file)

# Constants
PUMPFUN_API_URL = "https://api.pumpfunadvanced.com/coins"  # Hypothetical API endpoint
DATABASE_NAME = "pumpfun_coins.db"
TABLE_NAME = "migrated_coins"
TWITTER_DATA_TABLE = "twitter_data"

# Initialize SQLite database
def init_db():
    conn = sqlite3.connect(DATABASE_NAME)
    cursor = conn.cursor()

    # Table for migrated coins
    cursor.execute(f"""
        CREATE TABLE IF NOT EXISTS {TABLE_NAME} (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            coin_name TEXT NOT NULL,
            migration_time DATETIME NOT NULL,
            price REAL,
            volume REAL,
            market_cap REAL,
            dev_name TEXT,
            contract_address TEXT,
            UNIQUE(coin_name, migration_time)
        )
    """)

    # Table for Twitter data
    cursor.execute(f"""
        CREATE TABLE IF NOT EXISTS {TWITTER_DATA_TABLE} (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            coin_name TEXT NOT NULL,
            twitter_handle TEXT,
            followers_count INTEGER,
            tweet_count INTEGER,
            last_tweet_time DATETIME,
            UNIQUE(coin_name, twitter_handle)
        )
    """)

    conn.commit()
    conn.close()

# Fetch data from PumpFun Advanced
def fetch_pumpfun_data():
    try:
        response = requests.get(PUMPFUN_API_URL)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        print(f"Error fetching PumpFun data: {e}")
        return None

# Fetch Twitter data
def fetch_twitter_data(coin_name, twitter_handle):
    try:
        twitter_api_url = f"{config['twitter']['api_url']}/user/{twitter_handle}"
        response = requests.get(twitter_api_url)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        print(f"Error fetching Twitter data for {coin_name}: {e}")
        return None

# Analyze data and execute trades
def analyze_and_trade():
    conn = sqlite3.connect(DATABASE_NAME)
    pumpfun_df = pd.read_sql_query(f"SELECT * FROM {TABLE_NAME}", conn)
    twitter_df = pd.read_sql_query(f"SELECT * FROM {TWITTER_DATA_TABLE}", conn)
    conn.close()

    # Merge data on coin_name
    merged_df = pd.merge(pumpfun_df, twitter_df, on="coin_name", how="inner")

    for _, row in merged_df.iterrows():
        if row["followers_count"] > config["twitter"]["min_followers"]:
            print(f"Trading {row['coin_name']} with {row['followers_count']} followers")

            # Execute trade here
            # trade(row["coin_name"], row["price"], row["volume"], row["contract_address"])