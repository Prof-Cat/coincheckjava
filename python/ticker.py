import json
import re

import pandas as pd

data = []
# Regular expression pattern to match JSON data within a line
matchJason = re.compile(r'{.*}')
matchExchangePair = re.compile(r'TargetExchange=(.*?)::TargetPair=(.*?)::')

with open('./logs/Testslf4j-20230422.log', 'r') as f:
    for line in f:
        foundJson = matchJason.search(line)
        if foundJson:
            json_data = foundJson.group()
            otherMatches = matchExchangePair.search(line)
            if otherMatches:
                xData = json.loads(json_data)
                xData['Exchange'] = otherMatches.group(1)
                xData['CoinPair'] = otherMatches.group(2)
                json_data = json.dumps(xData)
                data.append(json_data)
                # print(data)
        else :
            print( "ignore " + line)
            
    df = pd.DataFrame(data)
    print(df)




