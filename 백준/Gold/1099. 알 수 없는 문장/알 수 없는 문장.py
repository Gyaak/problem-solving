import sys

NOTVISITED = 99999999
UNINTERPRETABLE = 999999

cache = []
max_word_len = 0

def setInput():
    sentence = sys.stdin.readline().strip()
    N = int(sys.stdin.readline().strip())
    words = [sys.stdin.readline().strip() for _ in range(N)]
    return sentence, words

def calc_cost(target: str, word: str):
    cost = 0
    for i in range(len(word)):
        cost += 1 if target[i]!=word[i] else 0
    return cost

def interpret_word(target: str, words: list):
    cost = UNINTERPRETABLE
    for word in words:
        if sorted(target) == sorted(word):
            cost = min(cost, calc_cost(target, word))
    return cost

def get_cache(n: int, sentence: str, words: list):
    if n<0:
        return 0
    # get_cache(n) := 0~n까지 문장을 해석하는데 최소 비용
    if cache[n] == NOTVISITED:
        cache[n] = UNINTERPRETABLE
        for i in range(min(max_word_len,n+1)):
            target = sentence[n-i:n+1]
            cost = interpret_word(target, words)
            if cost != UNINTERPRETABLE:
                cache[n] = min(cache[n], get_cache(n-i-1, sentence, words) + cost)
    return cache[n]

def solve(sentence: str, words: list):
    cost = get_cache(len(sentence)-1, sentence, words)
    if cost == UNINTERPRETABLE:
        return -1
    return cost

if __name__ == "__main__":
    sentence, words = setInput()
    max_word_len = max_word_len = max(len(word) for word in words)
    cache = [NOTVISITED] * len(sentence)
    print(solve(sentence, words))
