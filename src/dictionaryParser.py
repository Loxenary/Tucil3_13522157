# read data from original dictionary
def read_original_data(file):
    with open(file, 'r') as f:
        data = [line.strip() for line in f]
    return data

# Check if the w1 and w2 have only 1 character diffrence
def isHavingOneDifferent(w1, w2):
    diff = 0
    for i in range(len(w1)):
        if w1[i] != w2[i]:
            diff += 1
        if diff > 1:
            return False
    if diff == 1:
        return True
    return False

# Collect and parse the original data into a file called parsed_data
def save_data(data):
    grouped_words = {}
    for word in data:
        length = len(word)
        if length not in grouped_words:
            grouped_words[length] = []
        grouped_words[length].append(word)
    sorted_lengths = sorted(grouped_words.keys())
    
    # Write the parsed data for each length group to the output file
    with open('src/data/parsed_data.txt', 'a') as f:
        for length in sorted_lengths:
            for word in grouped_words[length]:
                related_words = [w for w in grouped_words[length] if isHavingOneDifferent(word, w)]
                if(len(related_words) != 0):
                    f.write(f'{word} {len(related_words)}\n')
                else:
                    f.write(f'{word} {0}\n')
                
                for related_word in related_words:
                    f.write(f'{related_word}\n')
            f.write("\n")
            print("Length",length,"Done")
        
data = read_original_data('src/data/originalData.txt')
save_data(data)