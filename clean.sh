git checkout --detach  # Detach from the current branch
git branch -a | grep remotes | grep -v HEAD | while read -r branch; do
    git checkout --track "$branch"
    find . -name ".DS_Store" -type f -delete
    git add .
    git commit -m 'rm DS_Store'
    git push
done
git checkout development  # or any branch you want to switch back to
