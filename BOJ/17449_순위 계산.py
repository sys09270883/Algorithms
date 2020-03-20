input = __import__('sys').stdin.readline
R = int(input())
N = int(input())
cnt = 0
ls = list(map(int, input().split()))
for x in ls:
    if x < R:
        R += 1
    elif x == R:
        cnt += 1
    elif x > R and R + cnt >= x:
        if R + cnt == x:
            cnt = 1
        else:
            cnt += R - x + 1
        R = x
print(R, R + cnt)